package cn.seeumt.security.loginmodel;

import cn.seeumt.model.OtpCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Seeumt
 * @version 1.0
 * @date 2020/2/2 19:21
 */
public class Otp implements Serializable {
    private static final long serialVersionUID = 3576792236323070770L;
    private String telephone;
    private String validCode;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    @Override
    public String toString() {
        return "Otp{" +
                "telephone='" + telephone + '\'' +
                ", validCode='" + validCode + '\'' +
                '}';
    }
}
