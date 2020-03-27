package com.syt.face.faceservice.test;

public class StudentBean {

    public class StudentBean {
        /**
         * action : INSERT
         * campusid : 196
         * className : 三年（1）班
         * classid : 401427117
         * committeeNames :
         * createTime : 2019-seven-twentyfour seventeen:55:51
         * datapath :
         * name : 分为非
         * picpath : http://qiniu.weixiaotong.com/boyteacher.jpg
         * sex : 1 性别1男：2女
         * stuNumber :
         * stuid : 1561257844
         */
        private Long studentID;
        private String action;
        private int campusid;
        private String className;
        private Long classid;
        private String committeeNames;
        private String createTime;
        private String datapath;
        private String facedataPath;//2.0
        private String name;
        private String picpath;
        private String sex;
        private String stuNumber;
        private int stuid;

        public StudentBean(Long studentID, String action, int campusid,
                           String className, Long classid, String committeeNames,
                           String createTime, String datapath, String facedataPath, String name,
                           String picpath, String sex, String stuNumber, int stuid) {
            this.studentID = studentID;
            this.action = action;
            this.campusid = campusid;
            this.className = className;
            this.classid = classid;
            this.committeeNames = committeeNames;
            this.createTime = createTime;
            this.datapath = datapath;
            this.facedataPath = facedataPath;
            this.name = name;
            this.picpath = picpath;
            this.sex = sex;
            this.stuNumber = stuNumber;
            this.stuid = stuid;
        }

        @Generated(hash = 2097171990)
        public StudentBean() {
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getCampusid() {
            return campusid;
        }

        public void setCampusid(int campusid) {
            this.campusid = campusid;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Long getClassid() {
            return classid;
        }

        public void setClassid(Long classid) {
            this.classid = classid;
        }

        public String getCommitteeNames() {
            return committeeNames;
        }

        public void setCommitteeNames(String committeeNames) {
            this.committeeNames = committeeNames;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDatapath() {
            return datapath;
        }

        public void setDatapath(String datapath) {
            this.datapath = datapath;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicpath() {
            return picpath;
        }

        public void setPicpath(String picpath) {
            this.picpath = picpath;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStuNumber() {
            return stuNumber;
        }

        public void setStuNumber(String stuNumber) {
            this.stuNumber = stuNumber;
        }

        public int getStuid() {
            return stuid;
        }

        public void setStuid(int stuid) {
            this.stuid = stuid;
        }

        public String getFacedataPath() {
            return this.facedataPath;
        }

        public void setFacedataPath(String facedataPath) {
            this.facedataPath = facedataPath;
        }

        public Long getStudentID() {
            return this.studentID;
        }

        public void setStudentID(Long studentID) {
            this.studentID = studentID;
        }


    }
