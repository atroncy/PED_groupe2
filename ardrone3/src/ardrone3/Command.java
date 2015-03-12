package ardrone3;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import javax.xml.bind.DatatypeConverter;

public class Command {
	
	
	
	
	//Attributes of a command to be sent to the drone:
	//header
	byte _type;
	byte _id;
	byte _seq;
	int _size;
	//body
	byte _project;
	byte _class;	//droneClass
	short _cmd;
	byte[] _arg;
	

	/**
	 * Create a new command to be sent. The different arguments depends on the type of command
	 * one does want to send. Please use the appropriate commands below to create the correct command.
	 * @param type
	 * @param id
	 * @param project
	 * @param droneClass
	 * @param command
	 */
	public Command (byte type, int id, byte seqNum, byte project, byte droneClass, short command){
		this._type		= (byte) type;
		this._id		= (byte) id;
		this._seq		= (byte) seqNum;
		this._project	= project;
		this._class		= droneClass;
		this._cmd 		= command;
	}
	
	
	/**
	 * Return the command in a human readable format.
	 */
	public String toString(){
		byte[] arr = this.commandToByteArray();
		return DatatypeConverter.printHexBinary(arr);
	}
	
	
	/**
	 * Convert an object Command into a byte of array.
	 * @param command
	 * @return
	 */
	private byte[] commandToByteArray(){
		byte[] array = new byte[this._size];
		array[0] = this._type;
		array[1] = this._id;
		array[2] = this._seq;
		byte[] size_array = intToByteArray(this._size);
		for (int i = 0, j = 3 ; i < 4 ; i++, j++) {
			array[j] = size_array[i];
		}
		array[7] = this._project;
		array[8] = this._class;
		byte[] cmd_array = shortToByteArray(this._cmd);
		array[9] =  cmd_array[0];
		array[10] = cmd_array[1];
		for (int i = 11, j = 0; i < this._size ; i++, j++) {
			array[i] = this._arg[j];
		}

		return array;
	}
	
	/**
	 * Convert an integer into a byte array in little endian format.
	 * @param value
	 * @return
	 */
	private static byte[] intToByteArray(int value){
	    return new byte[] {
	            (byte)value,
	            (byte)(value >>> 8),
	            (byte)(value >>> 16),
	            (byte)(value >>> 24)};
	}
	
	/**
	 * Convert a short into a byte array in little endian format.
	 * @param value
	 * @return
	 */
	private static byte[] shortToByteArray(short value){
	    return new byte[] {
	    		(byte)value,
	    		(byte)(value >>> 8)};
	}
	

}

	

