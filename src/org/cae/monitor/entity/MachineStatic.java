package org.cae.monitor.entity;

import java.util.List;

import org.cae.entity.Entity;

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
	
	class FileSystem{
		private String fileSystemName;
		private Integer fileSystemAvail;
		public String getFileSystemName() {
			return fileSystemName;
		}
		public void setFileSystemName(String fileSystemName) {
			this.fileSystemName = fileSystemName;
		}
		public Integer getFileSystemAvail() {
			return fileSystemAvail;
		}
		public void setFileSystemAvail(Integer fileSystemAvail) {
			this.fileSystemAvail = fileSystemAvail;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsDescription() {
		return osDescription;
	}

	public void setOsDescription(String osDescription) {
		this.osDescription = osDescription;
	}

	public String getOsVendor() {
		return osVendor;
	}

	public void setOsVendor(String osVendor) {
		this.osVendor = osVendor;
	}

	public Integer getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(Integer cpuNum) {
		this.cpuNum = cpuNum;
	}

	public Integer getMemoryTotalSize() {
		return memoryTotalSize;
	}

	public void setMemoryTotalSize(Integer memoryTotalSize) {
		this.memoryTotalSize = memoryTotalSize;
	}

	public Integer getFileSystemTotalSize() {
		return fileSystemTotalSize;
	}

	public void setFileSystemTotalSize(Integer fileSystemTotalSize) {
		this.fileSystemTotalSize = fileSystemTotalSize;
	}

	public Integer getSwapSize() {
		return swapSize;
	}

	public void setSwapSize(Integer swapSize) {
		this.swapSize = swapSize;
	}

	public List<FileSystem> getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(List<FileSystem> fileSystem) {
		this.fileSystem = fileSystem;
	}
	
}
