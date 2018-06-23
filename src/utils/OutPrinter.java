package utils;

import conts.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.util.List;

/**
 *
 */
public class OutPrinter {
    private PrintWriter mOut;
    private static OutPrinter mPrinter;

    private OutPrinter(PrintWriter printWriter) {
        mOut = printWriter;
    }

    public static OutPrinter getInstance(PrintWriter printWriter) {
        mPrinter = new OutPrinter(printWriter);
        return mPrinter;
    }

    public void printErr(int message) {
        printErr(String.valueOf(message));
    }

    public void printErr(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Config.CODE, 1);
        jsonObject.put(Config.ERROR_CODE, message);
        if (mPrinter != null &&
                mOut != null) {
            mOut.print(jsonObject);
            mOut.flush();
            mOut.close();
        }
    }

    public void print(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        out(jsonObject);
    }

    public void printArray(Object obj){
        out(obj);
    }
    private void out(Object obj) {
        if (mPrinter != null &&
                mOut != null) {
            mOut.print(obj);
            if (mOut != null) {
                mOut.flush();
            }
            mOut.close();
        }
    }

}
