package model;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

//An abstraction of a class that allows to convinently change JPA LockModeType
public class Locking {

	//change these lock modes and it will take effect throughtout the appln
	static LockModeType readLockType = LockModeType.PESSIMISTIC_READ;
	static LockModeType writeLockType = LockModeType.PESSIMISTIC_WRITE;

	public static void readLock(EntityManager em, Object o) {
		em.lock(o, readLockType);
	}

	public static void writeLock(EntityManager em, Object o) {
		em.lock(o, writeLockType);
	}

	public static LockModeType getReadLockType()  {
		return readLockType;
	}

	public static LockModeType getWriteLockType()  {
		return writeLockType;
	}
}