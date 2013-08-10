package org.xdb.spotgres.aws.ondemand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xdb.spotgres.aws.ondemand.AWSOnDemandPriceRoot.Config.Region.InstanceType.Size;
import org.xdb.spotgres.aws.ondemand.AWSOnDemandPriceRoot.Config.Region.InstanceType.Size.ValueColumn;

public class AWSOnDemandPriceRoot {
	
	private static Map<String, String[]> typeTranslation = new HashMap<String, String[]>();
	static {
		typeTranslation.put("m1.small", new String[]{"stdODI", "sm"});		    
		typeTranslation.put("m1.medium" , new String[]{"stdODI", "med"});
		typeTranslation.put("m1.large" , new String[]{"stdODI", "lg"});
		typeTranslation.put("m1.xlarge" , new String[]{"stdODI", "xl"});
		typeTranslation.put("m3.xlarge" , new String[]{"secgenstdODI", "xl"});
		typeTranslation.put("m3.2xlarge" , new String[]{"secgenstdODI", "xxl"});
		typeTranslation.put("t1.micro" , new String[]{"uODI", "u"});
		typeTranslation.put("m2.xlarge" , new String[]{"hiMemODI", "xl"});
		typeTranslation.put("m2.2xlarge" , new String[]{"hiMemODI", "xxl"});
		typeTranslation.put("m2.4xlarge" , new String[]{"hiMemODI", "xxxxl"});
		typeTranslation.put("c1.medium" , new String[]{"hiCPUODI", "med"});
		typeTranslation.put("c1.xlarge" , new String[]{"hiCPUODI", "xl"});
		typeTranslation.put("cc1.4xlarge" , new String[]{"clusterComputeI", "xxxxl"});
		typeTranslation.put("cc2.8xlarge" , new String[]{"clusterComputeI", "xxxxxxxxl"});
		typeTranslation.put("cg1.4xlarge" , new String[]{"clusterGPUI", "xxxxl"});
		typeTranslation.put("hi1.4xlarge" , new String[]{"hiIoODI", "xxxx1"});
		}
		
	private static Map<String, String> regionTranslation = new HashMap<String, String>();
	static {
	    regionTranslation.put("us-east-1" , "us-east");
	    regionTranslation.put("us-west-2" , "us-west-2");
	    regionTranslation.put("us-west-1" , "us-west");
	    regionTranslation.put("eu-west-1" , "eu-ireland");
	    regionTranslation.put("ap-southeast-1" , "apac-sin");
	    regionTranslation.put("ap-northeast-1" , "apac-tokyo");
	    regionTranslation.put("sa-east-1" , "sa-east-1");

	}
	
	public static class Config {
		public static class Region {
			@Override
			public String toString() {
				return "Region [region=" + region + ", instanceTypes="
						+ instanceTypes + "]";
			}
			public static class InstanceType {
				@Override
				public String toString() {
					return "InstanceType [type=" + type + ", sizes=" + sizes
							+ "]";
				}
				public static class Size {
					@Override
					public String toString() {
						return "Size [size=" + size + ", valueColumns="
								+ valueColumns + "]";
					}
					public static class ValueColumn {
						private OSTypes name;
						private Map<Currencies, String> prices;
						public OSTypes getName() {
							return name;
						}
						public void setName(OSTypes name) {
							this.name = name;
						}
						public Map<Currencies, String> getPrices() {
							return prices;
						}
						public void setPrices(Map<Currencies, String> prices) {
							this.prices = prices;
						}
					}
					private String size;
					private HashMap<OSTypes, ValueColumn> valueColumns;
					
					public ArrayList<ValueColumn> getValueColumns() {
						return new ArrayList<ValueColumn>(this.valueColumns.values());
					}
					public void setValueColumns(ArrayList<ValueColumn> valueColumns) {
						this.valueColumns = new HashMap<OSTypes, ValueColumn>();
						for (ValueColumn currentElement:valueColumns){
							this.valueColumns.put(currentElement.getName(), currentElement);
						}
					}
					
					public ValueColumn getValueColumnByOSType(OSTypes osType){
						return this.valueColumns.get(osType);
					}
					public String getSize() {
						return size;
					}
					public void setSize(String size) {
						this.size = size;
					}
				}
				private String type;
				private HashMap<String, Size> sizes;
				public String getType() {
					return type;
				}
				public void setType(String type) {
					this.type = type;
				}
				public ArrayList<Size> getSizes() {
					return new ArrayList<Size>(this.sizes.values());
				}
				public void setSizes(ArrayList<Size> sizes) {
					this.sizes = new HashMap<String, Size>();
					for (Size currentElement:sizes){
						this.sizes.put(currentElement.getSize(), currentElement);
					}
				}
				public Size getSizeByName(String name){
					return sizes.get(name);
				}
			}
			private String region;
			private HashMap<String, InstanceType> instanceTypes;
			public String getRegion() {
				return region;
			}
			public void setRegion(String region) {
				this.region = region;
			}
			public ArrayList<InstanceType> getInstanceTypes() {
				 return new ArrayList<InstanceType>(this.instanceTypes.values());
			}
			public void setInstanceTypes(ArrayList<InstanceType> instanceTypes) {
				this.instanceTypes =  new HashMap<String, InstanceType>();
				for (InstanceType currentElement:instanceTypes){
					this.instanceTypes.put(currentElement.getType(), currentElement);
				}
			}
			public float getInstanceTypeByName(String typeName){
				float returnValue = -1.0f;
				String[] instanceTypeData = typeTranslation.get(typeName);
				InstanceType it = instanceTypes.get(instanceTypeData[0]);
				if (it != null){
					Size size = it.getSizeByName(instanceTypeData[1]);
					if (size != null){
						ValueColumn vc = size.getValueColumnByOSType(OSTypes.linux);
						if (vc != null){
							returnValue = Float.valueOf(vc.getPrices().get(Currencies.USD));						
						}
					}
					
				}
				return returnValue;
			}
			
		}
		private String rate;
		private ArrayList<String> valueColumns;
		public enum Currencies { USD };
		public enum OSTypes { linux, mswin };
		private ArrayList<String> currencies;
		private HashMap<String, Region> regions;
		public String getRate() {
			return rate;
		}
		public void setRate(String rate) {
			this.rate = rate;
		}
		public ArrayList<String> getCurrencies() {
			return currencies;
		}
		public void setCurrencies(ArrayList<String> currencies) {
			this.currencies = currencies;
		}
		
		public Region getRegionByName(String regionName){
			return this.regions.get(regionTranslation.get(regionName));
		}
		
		public ArrayList<Region> getRegions() {
			return new ArrayList<Region>(regions.values());
		}
		public void setRegions(ArrayList<Region> regions) {		
			this.regions = new HashMap<String, Region>();
			for (Region currentElement:regions){
				this.regions.put(currentElement.getRegion(), currentElement);
			}
		}
		public ArrayList<String> getValueColumns() {
			return valueColumns;
		}
		public void setValueColumns(ArrayList<String> valueColumns) {
			this.valueColumns = valueColumns;
		}

	}
	private float vers;
	private Config config;

	
	public float getVers() {
		return vers;
	}
	public void setVers(float vers) {
		this.vers = vers;
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}

	
}
