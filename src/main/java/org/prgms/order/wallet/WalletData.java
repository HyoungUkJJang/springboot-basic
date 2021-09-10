package org.prgms.order.wallet;

import java.time.LocalDateTime;
import java.util.UUID;

public class WalletData {
    private final UUID walletId;
    private UUID customerId;
    private UUID voucherId;
    private LocalDateTime createdAt;
    private boolean used;

    public WalletData(UUID walletId, boolean used) {
        this.walletId = walletId;
        this.used = used;
    }

    public WalletData(UUID walletId, UUID customerId, UUID voucherId) {
        this.walletId = walletId;
        this.customerId = customerId;
        this.voucherId = voucherId;
    }

    public WalletData(UUID walletId, UUID customerId, UUID voucherId, LocalDateTime createdAt) {
        this.walletId = walletId;
        this.customerId = customerId;
        this.voucherId = voucherId;
        this.createdAt = createdAt;
    }

    public WalletData(UUID walletId, UUID customerId, UUID voucherId, LocalDateTime createdAt, boolean used) {
        this.walletId = walletId;
        this.customerId = customerId;
        this.voucherId = voucherId;
        this.createdAt = createdAt;
        this.used = used;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getVoucherId() {
        return voucherId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean getUsed() {
        return used;
    }
}
