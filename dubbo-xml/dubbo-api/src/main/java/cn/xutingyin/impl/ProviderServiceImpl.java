package cn.xutingyin.impl;

import cn.xutingyin.ProviderService;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello ".concat(name);
    }
}
