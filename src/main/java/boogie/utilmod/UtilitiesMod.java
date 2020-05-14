package boogie.utilmod;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import static org.apache.logging.log4j.LogManager.getLogger;

public class UtilitiesMod implements ModInitializer {
    final Logger LOGGER = getLogger();
    final Marker MARKER = MarkerManager.getMarker("UsefulUtilities");
    @Override
    public void onInitialize() {
        LOGGER.warn(MARKER,"*********************************");
        LOGGER.warn(MARKER,"*Note:-                         *");
        LOGGER.warn(MARKER,"*This mod was made for 1.14.4   *");
        LOGGER.warn(MARKER,"*1.14.4 is no longer supported  *");
        LOGGER.warn(MARKER,"*Please update to recieve more  *");
        LOGGER.warn(MARKER,"*features                       *");
        LOGGER.warn(MARKER,"*********************************");
    }
}
