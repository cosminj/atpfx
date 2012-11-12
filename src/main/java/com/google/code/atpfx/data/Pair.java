/**
 * 
 */
package com.google.code.atpfx.data;

/**
 * @author Andrei
 * Lists and indexes all pairs.
 * The pairs will be considered constants, but will still be in database.
 */
public class Pair {

	private long pairId;
	private String pairName;//a.k.a Security in FIX terminology
	
	public Pair() {}

    public Pair(long pairId, String name) {
        this.pairId = pairId;
        pairName = name;
    }
	
	public long getPairId() {
		return pairId;
	}
	public void setPairId(long pairId) {
		this.pairId = pairId;
	}
    
	public String getPairName() {
		return pairName;
	}
	public void setPairName(String pairName) {
		this.pairName = pairName;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        return pairId == pair.pairId;

    }

    @Override
    public int hashCode() {
        return (int) (pairId ^ (pairId >>> 32));
    }

    @Override
    public String toString() {
        return "Pair{" +
                "pairId=" + pairId +
                ", pairName='" + pairName + '\'' +
                '}';
    }
}