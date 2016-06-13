package com.qcloud.project.forest.web.form;

import java.util.List;
import com.qcloud.project.forest.web.vo.MedicationRemindersVO;

public class MedicationRemindersForm {

    private long                id;

    // 名称
    private String              name;

    // 图片
    private String              image;

    private String              uid;

    List<MedicationRemindersVO> medicationRemindersVOs;

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public MedicationRemindersForm() {

    }

    public List<MedicationRemindersVO> getMedicationRemindersVOs() {

        return medicationRemindersVOs;
    }

    public void setMedicationRemindersVOs(List<MedicationRemindersVO> medicationRemindersVOs) {

        this.medicationRemindersVOs = medicationRemindersVOs;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public String getImage() {

        return image;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }
}
