package com.qcloud.component.my.util;

public class RedisKeyUtils {

    public static final int MYWEALTHTIMESECONDS = 60 * 60 * 24 * 15;

    // key定义前缀规则：String->s;Hash-->m;List-->l;Set-->s;SortedSet-->x;
//    /**
//     * token key
//     * 
//     * @param tokenId
//     * @return
//     */
//    public static String getUserIdKey(long userId) {
//
//        return "s:u_id:" + userId;
//    }
//      
//    /**
//     * mobile key
//     * 
//     * @param mobile
//     * @return
//     */
//    public static String getUserMobileKey(String mobile) {
//
//        return "s:u_mb:" + mobile;
//    }
//
//    /**
//     * email key
//     * 
//     * @param email
//     * @return
//     */
//    public static String getUserEmailKey(String email) {
//
//        return "s:u_em:" + email;
//    }
//
//    /**
//     * membershipCard key
//     * 
//     * @param membershipCard
//     * @return
//     */
//    public static String getUserMembershipCardKey(String membershipCard) {
//
//        return "s:u_mc:" + membershipCard;
//    }
//    
//    /**
//     * online key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getOnlineUserKey() {
//
//        return "x:u_online";
//    }
//
//    /**
//     * online key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getOnlineUserItemKey(long userId) {
//
//        return "s:u_ol:" + userId;
//    }
//
//    /**
//     * online key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getHighQualityUserKey() {
//
//        return "x:u_quality";
//    }
//
//    /**
//     * high quality key
//     * 
//     * @param account
//     * @return
//     */
//    public static String getHighQualityUserItemKey(long userId) {
//
//        return "s:u_hq:" + userId;
//    }
//
//    /**
//     * online key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getRecentlyLoginUserKey() {
//
//        return "x:u_login";
//    }
//
//    /**
//     * recently login key
//     *  
//     * @param userId
//     * @return
//     */
//    public static String getRecentlyLoginUserItemKey(long userId) {
//
//        return "s:u_rl:" + userId;
//    }
//
//    /**
//     * online key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getActiveLoginUserKey() {
//
//        return "x:u_active";
//    }
//
//    /**
//     * active key
//     * 
//     * @param userId
//     * @return
//     */
//    public static String getActiveLoginUserItemKey(long userId) {
//
//        return "s:u_at:" + userId;
//    }
//
//    public static String getWealthKey(long userId) {
//
//        return "s:my_w:" + userId;
//    }
}
