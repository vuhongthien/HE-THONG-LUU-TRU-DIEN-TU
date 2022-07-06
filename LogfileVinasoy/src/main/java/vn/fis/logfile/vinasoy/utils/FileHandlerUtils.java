package vn.fis.logfile.vinasoy.utils;

public class FileHandlerUtils {

    public static String regexRemoveSpecialCharacter( String str) {
        return str.replaceAll("[^A-Za-z0-9]", "");  // xoa ky tu dac bieu
    }

    public static String createServerFileNameUtils( String idEmployee, String idTask, String originalFileName) {
        return regexRemoveSpecialCharacter(idTask) + "_" + regexRemoveSpecialCharacter(idEmployee) + "_" + originalFileName;
    }

    public static String getTypeOfFile(String serverFileName) {
        return serverFileName.substring(serverFileName.lastIndexOf("."));
    }

    public static String getOriginalFileNameFromServerFileName(String serverFileName) {
        String s1 = serverFileName.substring(serverFileName.indexOf('_') + 1);
        return s1.substring(s1.indexOf('_') + 1);
    }


    public static void main(String[] args) {
        String idEmployee = "NV@@123@!#";
        String idTask = "123@!#";
        String originalFileName = "True file name";

        System.out.println("Create server file name : " + createServerFileNameUtils(idEmployee,idTask,originalFileName));
    }
}
