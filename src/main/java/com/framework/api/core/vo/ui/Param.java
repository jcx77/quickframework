package com.framework.api.core.vo.ui;

import java.io.Serializable;

public class Param implements Serializable {
    private static final long serialVersionUID = 1L;
    private String project;
    private String version;
    private String fileHangPath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    private String copyright;
    private String filepath;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getFileHangPath() {
        return fileHangPath;
    }

    public void setFileHangPath(String fileHangPath) {
        this.fileHangPath = fileHangPath;
    }
}