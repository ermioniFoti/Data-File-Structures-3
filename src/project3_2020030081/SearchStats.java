package project3_2020030081;

class SearchStats {
	
	static float nodeAccessCount ;
	static float compareCount ;
	
	static void increaseNodeAccessCount(){
		nodeAccessCount ++;		
	}
	
	static void increaseCompareCount() {
		compareCount ++;
	}

	public static float getNodeAccessCount() {
		return nodeAccessCount;
	}

	public static void setNodeAccessCount(int nodeAccessCount) {
		SearchStats.nodeAccessCount = nodeAccessCount;
	}

	public static float getCompareCount() {
		return compareCount;
	}

	public static void setCompareCount(int compareCount) {
		SearchStats.compareCount = compareCount;
	}
	
	
	
	
}
