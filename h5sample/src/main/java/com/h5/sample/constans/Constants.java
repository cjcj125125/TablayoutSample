package com.h5.sample.constans;

import java.net.URL;

/**
 * Created by Right on 2016/8/19.
 */
public class Constants {
    //http://192.168.1.105:8083/swagger-ui.html#!
    /**
     * 请求地址前缀
     */
 public final static String URL_HEAD = "http://clientapi.zjyiwei.com:10080/";
 //public final static String URL_HEAD = "http://192.168.1.17:8088/";
 // public final static String URL_HEAD = "http://192.168.1.121:8083/";
    // public final static String URL_HEAD="http://api.rightsidetech.com:10080/";
    /**
     * 添加购物车
     */
    public final static String ADD_CARSHOP = URL_HEAD + "cart/add";

    /****
     * 取消订单
     */
    public final static String CALCLE_ORDER = URL_HEAD + "order/cancel";
    /**
     * 购物车修改数量
     */
    public final static String CARNUM = URL_HEAD + "cart/count/update";
    /* 查询购物车
    * */
    public final static String QUAR_CARSHOP = URL_HEAD + "cart/query";
    /* 删除购物车
    * */
    public final static String DEL_CARSHOP = URL_HEAD + "cart/remove/";
    /* 清空购物车
   * */
    public final static String CLEAN_CARSHOP = URL_HEAD + "cart/clear";
    /* 登录
       * */
    public final static String Login = URL_HEAD + "user/login";

    /* 退出登录
         * */
    public final static String LoginOut = URL_HEAD + "logout/";

    /* 查询所有分类
    -1:查询所有分类,0:查询所有一级分类,n:查询id为n的子分类列表
       * */
    public final static String AllList = URL_HEAD + "category/list/%7BparentId%7D";

    /****
     * 通过商品分类ID,品牌ID查询列表
     */
    public final static String Type_List = URL_HEAD + "item/by_categoryId_brandId";

    /****
     * 查询某一个商品详情
     */
    public final static String GoodsDetails = URL_HEAD + "item/find/by_itemId";
    /****
     * 查询用户地址
     */
    public final static String GETADDRESS = URL_HEAD + "address/all/query";
    /****
     * 添加地址
     */
    public final static String ADDRESS = URL_HEAD + "address/add";
    /****
     * 查询所有收货地址
     */
    public final static String FINDADDRESS = URL_HEAD + "address/all/query";
    /****
     * 修改收货地址
     */
    public final static String UPDATEADDRESS = URL_HEAD + "address/update";
    /****
     * 删除地址
     */
    public final static String DELADDRESS = URL_HEAD + "address/delete";
    /****
     * 下单的接口
     */
    public final static String SAVEORDER = URL_HEAD + "order/save";
    /***
     * 所有订单查询
     */
    public final static String ALLORDER = URL_HEAD + "order/conditions/query";
    /***
     * 添加到收藏
     */
    public final static String ADDCOLLECET = URL_HEAD + "favorite/save/";
    /***
     * 取消订单
     */
    public final static String CANCLEORDER = URL_HEAD + "order/update/status";
    /***
     * 删除订单
     */
    public final static String DELORDER = URL_HEAD + "order/delete";
    /***
     * 批量查询订单，购物车点击下单按钮操作
     */
    public final static String BATCHORDER = URL_HEAD + "cart/list";
    /***
     * 查询全部收藏
     */
    public final static String ALLMYCOLLECT = URL_HEAD + "favorite/find/";
    /***
     * 取消收藏
     */
    public final static String DELMOLLECT = URL_HEAD + "favorite/delete/";
    /***
     * 查询首页广告图
     */
    public final static String MAINADV = URL_HEAD + "home/advert/find";
    /***
     * 模糊搜索
     */
    public final static String QUARYFUZZ = URL_HEAD + "item/by_name";
    /***
     * 查詢品牌
     */
    public final static String SHOWBRAND = URL_HEAD + "brand/find_list";
    /***
     * 首页推荐商品
     */
    public final static String RECOMM = URL_HEAD + "item/recommended";
    /***
     * 获取短信验证码
     */
    public final static String SENDSMS = URL_HEAD + "user/authcode/send";
    /***
     * 验证手机号码验证码
     */
    public final static String CHECKPHONE = URL_HEAD + "user/code/check";
    /***
     * 正式开始注册
     */
    public final static String GOREGIS = URL_HEAD + "user/registry";
    /***
     * 微信支付
     */
    public final static String WXPAY = URL_HEAD + "/pay/weChatPay";

    /***
     * 订单详情
     */
    public final static String ORDERPARDETAILS = URL_HEAD + "order/list/ids";
    /***
     * 修改个人信息
     */
    public final static String UPUSERINFO = URL_HEAD + "userData/update";
    /***
     * 修改密码
     */
    public final static String UPPASSWORD = URL_HEAD + "userData/update/by_id";

    //http://client.rightsidetech.com:10080/detail/h5view.html?itemID=355
    /***
     * 商品详情查询itemID=355
     */
    public final static String ORDERDETAILS = URL_HEAD + "http://client.rightsidetech.com:10080/detail/h5view.html?itemID=";
    /***
     * 查询用户信息
     */
    public final static String USERDATA = URL_HEAD + "userData/by_accountId";
    /***
     * 根据用户名查询手机号
     */
    public final static String FINDPHONEBYNAME = URL_HEAD + "userData/find/by_name";
    /***
     * 找回密码最后确认
     */
    public final static String FORPASS = URL_HEAD + "userData/find/account";
    /***
     * 头像上传
     */
    public final static String HEADPIC = URL_HEAD + "pic/upload/";
    /***
     * 购买记录
     */
    public final static String BUYHISTORY = URL_HEAD + "order/purchase_records";
    /**
     * 银联支付
     **/
    public final static String UNPAY = URL_HEAD + "/union/unionPay";

    /**
     * 支付宝支付
     **/
    public final static String ALIPAY = URL_HEAD + "/alipay/pay";
//    1000 成功
//    1001 失败
//    1002用户名或密码不能为空
//    1002 用户不存在
//    1003 密码错误
//    1004用户名已存在
//    1005 该手机号码已注册，去登陆吧
//    1006 用户名超过限制长度
//    1007 密码长度6-18位
//    1008 登录已过期
//    1009 请先登录
//    1010 该商品已下架
//    1011 无效地址
//    1012 参数格式错误
//    1013 账户已锁定，找回密码 解锁
//    3 您还可以输入%d次


}
