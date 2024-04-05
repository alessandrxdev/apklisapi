package com.arr.apklislib.update.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LastRelease {
    @SerializedName("abi")
    private List<ABI> abi;
    @SerializedName("app_name")
    private String appName;
    @SerializedName("beta")
    private boolean beta;
    @SerializedName("changelog")
    private String changelog;
    @SerializedName("deleted")
    private boolean deleted;
    @SerializedName("developer")
    private String developer;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private long id;
    @SerializedName("no_abi")
    private boolean noABI;
    @SerializedName("package_name")
    private String packageName;
    @SerializedName("permissions")
    private List<Permission> permissions;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("published")
    private String published;
    @SerializedName("screenshots")
    private List<Screenshot> screenshots;
    @SerializedName("sha256")
    private String sha256;
    @SerializedName("size")
    private String size;
    @SerializedName("version_code")
    private long versionCode;
    @SerializedName("version_name")
    private String versionName;
    @SerializedName("version_sdk")
    private String versionSDK;
    @SerializedName("version_sdk_name")
    private String versionSDKName;
    @SerializedName("version_target_sdk")
    private long versionTargetSDK;
    @SerializedName("version_target_sdk_name")
    private long versionTargetSDKName;

    public List<ABI> getABI() { return abi; }

    public String getAppName() { return appName; }

    public boolean getBeta() { return beta; }

    public String getChangelog() { return changelog; }

    public boolean getDeleted() { return deleted; }

    public String getDeveloper() { return developer; }

    public String getIcon() { return icon; }

    public long getID() { return id; }

    public boolean getNoABI() { return noABI; }

    public String getPackageName() { return packageName; }

    public List<Permission> getPermissions() { return permissions; }

    public boolean getPublic() { return isPublic; }

    public String getPublished() { return published; }

    public List<Screenshot> getScreenshots() { return screenshots; }

    public String getSha256() { return sha256; }

    public String getSize() { return size; }

    public long getVersionCode() { return versionCode; }

    public String getVersionName() { return versionName; }

    public String getVersionSDK() { return versionSDK; }

    public String getVersionSDKName() { return versionSDKName; }

    public long getVersionTargetSDK() { return versionTargetSDK; }

    public long getVersionTargetSDKName() { return versionTargetSDKName; }
}
