package cn.xutingyin.service.impl;

import cn.xutingyin.service.ProviderService;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello ".concat(name);
    }
}
