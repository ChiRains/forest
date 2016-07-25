package com.qcloud.project.forest.web.vo.admin;

public class AdminRangeGradeVO {

    private long   id;

    private long   rangeId;

    private String rangeName;

    private long   gradeId;

    private String gradeName;

    public AdminRangeGradeVO() {

    }

    public void setId(long id) {

        this.id = id;
    }

    public long getId() {

        return id;
    }

    public void setRangeId(long rangeId) {

        this.rangeId = rangeId;
    }

    public long getRangeId() {

        return rangeId;
    }

    public void setGradeId(long gradeId) {

        this.gradeId = gradeId;
    }

    public long getGradeId() {

        return gradeId;
    }

    public String getGradeName() {

        return gradeName;
    }

    public void setGradeName(String gradeName) {

        this.gradeName = gradeName;
    }

    public String getRangeName() {

        return rangeName;
    }

    public void setRangeName(String rangeName) {

        this.rangeName = rangeName;
    }
}
