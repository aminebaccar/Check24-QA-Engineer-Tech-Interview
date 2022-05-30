package exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidOptionException extends Exception{
    Logger LOGGER = LogManager.getLogger(InvalidOptionException.class);

    public InvalidOptionException(){
        LOGGER.error("Value provided is invalid, please input valid test data");
        LOGGER.error(super.getStackTrace());
    }
}
