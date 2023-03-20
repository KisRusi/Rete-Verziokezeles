package hu.bme.mit.train.interfaces;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	void activateEmergencyBreak();

	void addToTachograf();

	Table getTacho();

}
