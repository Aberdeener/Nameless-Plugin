package com.namelessmc.plugin.common;

import com.namelessmc.java_api.NamelessException;
import com.namelessmc.java_api.NamelessUser;
import com.namelessmc.plugin.common.event.NamelessPlayerBanEvent;
import net.kyori.event.EventSubscription;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;
import java.util.UUID;

public class SyncBanToWebsite implements Reloadable {

	private final NamelessPlugin plugin;

	private @Nullable EventSubscription subscription;

	SyncBanToWebsite(final NamelessPlugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public void reload() {
		if (subscription != null) {
			subscription.unsubscribe();
			subscription = null;
		}

		if (!this.plugin.config().main().node("sync-ban-to-website").getBoolean()) {
			return;
		}

		this.plugin.events().subscribe(NamelessPlayerBanEvent.class, event -> {
			final UUID uuid = event.uuid();

			this.plugin.scheduler().runAsync(() -> {
				this.plugin.apiProvider().api().ifPresentOrElse(api -> {
					try {
						Optional<NamelessUser> userOptional = api.getUserByMinecraftUuid(uuid);
						if (userOptional.isPresent()) {
							final NamelessUser user = userOptional.get();
							if (user.isBanned()) {
								this.plugin.logger().info("User " + user.getUsername() + " is already banned");
							} else {
								user.banUser();
								this.plugin.logger().info("Banned user on website");
							}
						}
					} catch (final NamelessException e) {
						this.plugin.logger().warning("Failed to ban player on website");
						this.plugin.logger().logException(e);
					}
				}, () -> this.plugin.logger().warning("Skipped trying to ban user, website connection is not working properly."));
			});
		});
	}

}
