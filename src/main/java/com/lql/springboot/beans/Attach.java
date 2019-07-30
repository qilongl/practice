package com.lql.springboot.beans;

/**
 * @Author lql
 * @Date 2018/7/1 13:42
 */
public class Attach {
    public String objId;
    public String objName;
    public String attachSize;
    public String attachType;
    public String path;
    public String type;
    public String fk_objId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(String attachSize) {
        this.attachSize = attachSize;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFk_objId() {
        return fk_objId;
    }

    public void setFk_objId(String fk_objId) {
        this.fk_objId = fk_objId;
    }

    @Override
    public String toString() {
        return "Attach{" +
                "objId='" + objId + '\'' +
                ", objName='" + objName + '\'' +
                ", attachSize='" + attachSize + '\'' +
                ", attachType='" + attachType + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", fk_objId='" + fk_objId + '\'' +
                '}';
    }
}
