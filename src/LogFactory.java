public class LogFactory {
    public Log getLog (String logType) {
        if (logType == null)
            return null;
        else if (logType.equalsIgnoreCase("user"))
            return new UserLog();

        return null;
    }
}
