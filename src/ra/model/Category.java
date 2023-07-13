package ra.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int idCategory;
    private String nameCtategory;
    private boolean status = true;

    public Category() {
    }


    public Category(int idCategory, String nameCtategory, boolean status) {
        this.idCategory = idCategory;
        this.nameCtategory = nameCtategory;
        this.status = status;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCtategory() {
        return nameCtategory;
    }

    public void setNameCtategory(String nameCtategory) {
        this.nameCtategory = nameCtategory;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Danh mục{" +
                "ID=" + idCategory +
                ", Tên danh mục='" + nameCtategory + '\'' +
                "Trạng thái: " + status + '}';
    }
}
