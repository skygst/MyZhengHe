package com.gst.frame.bean;

/**
 * Created by gst-pc on 2017/2/7.
 */

public class PhoneResult {

    /**
     * {
     *      "errNum": 0,
     *      "retMsg": "success",
     *      "retData": {
     *          "phone": "13671953787",
     *          "prefix": "1367195",
     *          "supplier": "移动",
     *          "province": "上海",
     *          "city": "上海",
     *          "suit": "136卡"
     * }
     */
    private int errNum;
    private String retMsg;
    private RetDataEntity retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public static class RetDataEntity {

        private String phone;
        private String prefix;
        private String supplier;
        private String province;
        private String city;
        private String suit;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getSuit() {
            return suit;
        }

        public void setSuit(String suit) {
            this.suit = suit;
        }

    }

}
