package razgriz.self.appexercise1202.module.home.model;

import razgriz.self.appexercise1202.bean.Pass;

public class PassSectionAndTypeModel {

    public interface IPassAdapterType {
        int STATUS_RESULT = 1;
        int PASS_TYPE_TITLE = 2;
        int PASS_CONTENT = 3;
        int FOOTER = 4;
    }

    private int type;
    private String content;
    private Pass pass;

    // for STATUS_RESULT and  PASS_TYPE_TITLE
    public PassSectionAndTypeModel(int type, String content) {
        this.type = type;
        this.content = content;
    }

    // for PASS_TYPE_CONTENT
    public PassSectionAndTypeModel(int type, Pass pass) {
        this.type = type;
        this.pass = pass;
    }

    // for FOOTER
    public PassSectionAndTypeModel(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Pass getPass() {
        return pass;
    }
}
