package com.luan.autojoin;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;

public class AutoJoinClient implements ClientModInitializer {
    private boolean hasJoined = false;

    @Override
    public void onInitializeClient() {
        // Đăng ký một sự kiện kiểm tra liên tục
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Nếu chưa vào server VÀ màn hình hiện tại là màn hình chính (Title Screen)
            if (!hasJoined && client.currentScreen instanceof TitleScreen) {
                hasJoined = true; // Đánh dấu đã kết nối để không lặp lại
                
                // Khai báo IP Server của bạn
                ServerAddress address = ServerAddress.parse("luckyvn.com");
                ServerInfo info = new ServerInfo("LuckyVN", "luckyvn.com", ServerInfo.ServerType.OTHER);
                
                // Ép client tự động thực thi lệnh kết nối
                ConnectScreen.connect(client.currentScreen, client, address, info, false, null);
            }
        });
    }
}
