package org.cae.monitor.entity;

import java.util.List;

import org.cae.common.Generator;
import org.cae.entity.Entity;
import org.cae.entity.IBuilder;

public class MachineStatic extends Entity {

	private String userName;
	private String computerName;
	private String userDomain;
	private String ip;
	private String hostName;
	private String osName;
	private String osArch;
	private String osVersion;
	private String osDescription;
	private String osVendor;
	private Integer cpuNum;
	private Integer memoryTotalSize;
	private Integer fileSystemTotalSize;
	private Integer swapSize;
	private List<FileSystem> fileSystem;
	
	private MachineStatic(Builder builder){
		this.userName=builder.userName;
		this.computerName=builder.computerName;
		this.userDomain=builder.userDomain;
		this.ip=builder.ip;
		this.hostName=builder.hostName;
		this.osName=builder.osName;
		this.osArch=builder.osArch;
		this.osVersion=builder.osVersion;
		this.osDescription=builder.osDescription;
		this.osVendor=builder.osVendor;
		this.cpuNum=builder.cpuNum;
		this.memoryTotalSize=builder.memoryTotalSize;
		this.fileSystemTotalSize=builder.fileSystemTotalSize;
		this.swapSize=builder.swapSize;
		this.fileSystem=builder.fileSystem;
	}
	public static class Builder implements IBuilder<MachineStatic>{

		private String userName;
		private String computerName;
		private String userDomain;
		private String ip;
		private String hostName;
		private String osName;
		private String osArch;
		private String osVersion;
		private String osDescription;
		private String osVendor;
		private Integer cpuNum;
		private Integer memoryTotalSize;
		private Integer fileSystemTotalSize;
		private Integer swapSize;
		private List<FileSystem> fileSystem;
		public Builder(){
			fileSystem=Generator.arrayList();
		}
		public Builder userName(String userName){
			this.userName=userName;
			return this;
		}
		public Builder computerName(String computerName){
			this.computerName=computerName;
			return this;
		}
		public Builder userDomain(String userDomain){
			this.userDomain=userDomain;
			return this;
		}
		public Builder ip(String ip){
			this.ip=ip;
			return this;
		}
		public Builder hostName(String hostName){
			this.hostName=hostName;
			return this;
		}
		public Builder osName(String osName){
			this.osName=osName;
			return this;
		}
		public Builder osArch(String osArch){
			this.osArch=osArch;
			return this;
		}
		public Builder osVersion(String osVersion){
			this.osVersion=osVersion;
			return this;
		}
		public Builder osDescription(String osDescription){
			this.osDescription=osDescription;
			return this;
		}
		public Builder osVendor(String osVendor){
			this.osVendor=osVendor;
			return this;
		}
		public Builder cpuNum(Integer cpuNum){
			this.cpuNum=cpuNum;
			return this;
		}
		public Builder memoryTotalSize(Integer memoryTotalSize){
			this.memoryTotalSize=memoryTotalSize;
			return this;
		}
		public Builder fileSystemTotalSize(Integer fileSystemTotalSize){
			this.fileSystemTotalSize=fileSystemTotalSize;
			return this;
		}
		public Builder swapSize(Integer swapSize){
			this.swapSize=swapSize;
			return this;
		}
		public Builder fileSystem(FileSystem fileSystem){
			this.fileSystem.add(fileSystem);
			return this;
		}
		@Override
		public MachineStatic build() {
			return new MachineStatic(this);
		}
		
	}
	
	class FileSystem{
		private String fileSystemName;
		private Integer fileSystemAvail;
		public FileSystem(String fileSystemName,Integer fileSystemAvail){
			this.fileSystemName=fileSystemName;
			this.fileSystemAvail=fileSystemAvail;
		}
		public String getFileSystemName() {
			return fileSystemName;
		}
		public Integer getFileSystemAvail() {
			return fileSystemAvail;
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getComputerName() {
		return computerName;
	}

	public String getUserDomain() {
		return userDomain;
	}

	public String getIp() {
		return ip;
	}

	public String getHostName() {
		return hostName;
	}

	public String getOsName() {
		return osName;
	}

	public String getOsArch() {
		return osArch;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public String getOsDescription() {
		return osDescription;
	}

	public String getOsVendor() {
		return osVendor;
	}

	public Integer getCpuNum() {
		return cpuNum;
	}

	public Integer getMemoryTotalSize() {
		return memoryTotalSize;
	}

	public Integer getFileSystemTotalSize() {
		return fileSystemTotalSize;
	}

	public Integer getSwapSize() {
		return swapSize;
	}

	public List<FileSystem> getFileSystem() {
		return fileSystem;
	}
	
}
