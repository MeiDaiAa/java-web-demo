package com.aliyun.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String rigion;


    public AliyunOSSProperties() {
    }

    public AliyunOSSProperties(String endpoint, String bucketName, String rigion) {
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.rigion = rigion;
    }

    /**
     * 获取
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 设置
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 获取
     * @return bucketName
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * 设置
     * @param bucketName
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 获取
     * @return rigion
     */
    public String getRigion() {
        return rigion;
    }

    /**
     * 设置
     * @param rigion
     */
    public void setRigion(String rigion) {
        this.rigion = rigion;
    }

    public String toString() {
        return "AliyunOSSProperties{endpoint = " + endpoint + ", bucketName = " + bucketName + ", rigion = " + rigion + "}";
    }
}

