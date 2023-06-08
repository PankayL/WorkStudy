package com.database.pojo;

/**
 * @author pankay
 * @create 2022-12-15-23:27
 */
public class CompanyType {
    private int typeNo;
    private String typeName;

    @Override
    public String toString() {
        return "CompanyType{" +
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

    public CompanyType(int typeNo, String typeName) {
        this.typeNo = typeNo;
        this.typeName = typeName;
    }

    public CompanyType() {
    }
}
