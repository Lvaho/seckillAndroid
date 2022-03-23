package com.zjc.keepwork.service;

public interface IUserService {
    public void doLogin(String mobile, String password);
    public void doRegister(String identity,String mobile,String nickname,String password);
}
