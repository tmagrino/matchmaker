package model;

import javax.persistence.LockModeType;

//An abstraction of a class that allows to convinently change JPA LockModeType
public class Locking {

	//change these lock modes and it will take effect throughtout the appln
	private static LockModeType readLockType = LockModeType.NONE;
	private 	static LockModeType writeLockType = LockModeType.OPTIMISTIC;

	public static LockModeType getReadLockType() {
		return readLockType;
	}

	public static LockModeType getWriteLockType() {
		return writeLockType;
	}
}

