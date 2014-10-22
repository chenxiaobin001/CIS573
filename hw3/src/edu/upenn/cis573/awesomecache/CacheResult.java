package edu.upenn.cis573.awesomecache;

public class CacheResult {
	
	private boolean hit;
	private String key;
	
	public CacheResult(boolean _hit, String _replacedKey) {
		hit = _hit;
		key = _replacedKey;
	}
	
	public CacheResult(boolean _hit) {
		hit = _hit;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public String getKey() {
		return key;
	}

}
