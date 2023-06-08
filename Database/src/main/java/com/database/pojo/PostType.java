package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-14-17:40
 */
public class PostType {
    private int typeNo;
    private String typeName;

    @Override
    public String toString() {
        return "PostType{" +
                "typeNo=" + typeNo +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    public int getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(int typeNo) {
        this.typeNo = typeNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PostType(int typeNo, String typeName) {
        this.typeNo = typeNo;
        this.typeName = typeName;
    }

    public PostType() {
    }
}
