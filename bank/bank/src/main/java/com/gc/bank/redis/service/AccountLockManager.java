package com.gc.bank.redis.service;

public interface AccountLockManager {

    void lock(Long accountId);

    void unlock(Long accountId);
}
