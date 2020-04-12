package com.bluesky.middleplatform.commons.config;

import java.io.File;
import java.util.Hashtable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Config {

    public static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    public static final String HOME_DIR_KEY = "HOMEDIR";
    public static String HOME_DIR_VALUE = null;

    private static String CONF_PATH = "config" + File.separator + "config.xml";
    private static final String URL_KEY = "URL";
    private static final String URL_HOME_KEY = "HOME";
    private static final String SEPARATOR = "$SEPARATOR$";
    private static Hashtable bsConfig = null;

    public static void main(String[] args) {
        System.out.println(System.getProperty("CacheSwitch"));
        System.out.println("catalina.home:" + System.getProperty("catalina.home"));
        System.out.println("user.dir:" + System.getProperty("user.dir"));
    }

    public static String getHomeDir() {
        if (HOME_DIR_VALUE == null) {
            loadConfig();
        }

        return HOME_DIR_VALUE;
    }

    public static String getHomeURL() {
        return getConfig("URL", "HOME");
    }

    public static String getConfig(String session, String key) {
        Hashtable h = getConfig(session);
        if ((h == null) || (h.get(key) == null)) {
            return null;
        }

        return h.get(key).toString().trim();
    }

    public static Hashtable getConfig(String session) {
        if (bsConfig == null) {
            loadConfig();
        }

        return (Hashtable) bsConfig.get(session);
    }

    public static boolean isDisable(String session, String key) {
        String value = getConfig(session, key);
        return isDisable(value);
    }

    public static boolean isDisable(String value) {
        if (value == null) {
            return true;
        }
        if (value.trim().equalsIgnoreCase("DISABLE")) {
            return true;
        }
        return false;
    }

    public static synchronized void loadConfig() {
        String sHomeDir = System.getProperty("HOMEDIR");
        System.out.println("sHomeDir===================" + sHomeDir);
        if ((sHomeDir == null) || ("".equals(sHomeDir))) {
            sHomeDir = "F:\\myProjects\\config";
        }

        Hashtable sysConfig = new Hashtable();
        sysConfig.put("HOMEDIR", sHomeDir);
        ConfigSax handler = new ConfigSax();
        try {
            XMLReader xr = XMLReaderFactory
                    .createXMLReader("org.apache.xerces.parsers.SAXParser");
            xr.setContentHandler((ContentHandler) handler);
            xr.setErrorHandler((ErrorHandler) handler);

            HOME_DIR_VALUE = sysConfig.get("HOMEDIR").toString()
                    + File.separator;
            InputStreamReader r = new InputStreamReader(new FileInputStream(
                    HOME_DIR_VALUE + CONF_PATH), "UTF-8");

            xr.parse(new InputSource(r));
            r.close();
        } catch (FileNotFoundException ex) {
            System.err
                    .println("Can't find the config.xml, please use -DHOMEDIR=homedir to set the homedir.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        bsConfig = handler.getNode();

        if (bsConfig == null) {
            System.err
                    .println(" config file not found. only load the system properites");
            bsConfig = new Hashtable();
        }

        dumpNode(bsConfig);

        bsConfig.put("SYSTEM", sysConfig);
    }

    protected static StringBuffer variableReplace(StringBuffer in) {
        String var = in.toString();
        while (true) {
            int start = var.indexOf("{HOMEDIR}");
            if (start == -1) {
                break;
            }
            var = var.substring(0, start) + HOME_DIR_VALUE
                    + var.substring(start + "HOMEDIR".length() + 2);
        }

        var = replaceFileSeparator(var);

        if (!var.equals(in)) {
            return new StringBuffer(var);
        }
        return null;
    }

    protected static String replaceFileSeparator(String path) {
        String returnValue = path;
        String sep = System.getProperty("file.separator");
        while (true) {
            int i = returnValue.indexOf("$SEPARATOR$");
            if (i == -1) {
                break;
            }
            returnValue = returnValue.substring(0, i)
                    + sep
                    + returnValue.substring(i + "$SEPARATOR$".length(),
                    returnValue.length());
        }

        return returnValue;
    }

    protected static void dumpNode(Hashtable node) {
        for (Enumeration e = node.keys(); e.hasMoreElements(); ) {
            String key = e.nextElement().toString();
            if ((node.get(key) instanceof Hashtable)) {
                dumpNode((Hashtable) node.get(key));
            } else {
                StringBuffer vr = variableReplace((StringBuffer) node.get(key));
                if (vr != null) {
                    node.remove(key);
                    node.put(key, vr);
                }
            }
        }
    }


}
