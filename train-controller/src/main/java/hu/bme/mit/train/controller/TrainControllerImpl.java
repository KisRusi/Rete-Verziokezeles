package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;
// import com.google.guava.Table;
import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	public Table<Long, Integer, Integer> Tachograf = HashBasedTable.create();

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		RenforceSpeedLimit();	
	}	


	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		RenforceSpeedLimit();
		
	}

	private void RenforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

	@Override
	public void activateEmergencyBreak(){
		while(referenceSpeed > 0)
		{
			referenceSpeed -= 10;
		}
	}

	@Override
	public void addToTachograf(){
		long time = System.currentTimeMillis();
		this.Tachograf.put(time, this.step, this.referenceSpeed );
		
	}

	@Override
	public int getReferenceSpeed(){
		return referenceSpeed;
	}
	@Override
	public Table getTacho(){
		return this.Tachograf;
	}

}
