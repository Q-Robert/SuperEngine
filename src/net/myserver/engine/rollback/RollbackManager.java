package net.myserver.engine.rollback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.Bukkit;

import net.myserver.engine.SuperEngine;

public class RollbackManager {
	
	private SuperEngine engine;
	
	public RollbackManager(SuperEngine engine)
	{
		this.engine = engine;
	}

	public boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (File file : files) {
					if (file.isDirectory()) {
						deleteDirectory(file);
					} else {
						file.delete();
					}
				}
			}
		}
		return directory.delete();
	}

	public void copyFolder(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdir();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				copyFolder(srcFile, destFile);
			}
		} else {
			OutputStream out;
			try (InputStream in = new FileInputStream(src)) {
				out = new FileOutputStream(dest);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			}
			out.close();
		}
	}
	
	
	public void rollback(final String world){
		Bukkit.getScheduler().runTaskLater(engine, new Runnable() {
			public void run() {
		File dir = new File(Bukkit.getWorld(world).getWorldFolder().getAbsolutePath());
		File mapFolder = new File(engine.getDataFolder(), world);
			deleteDirectory(dir);
			new File(Bukkit.getServer().getWorldContainer().getAbsolutePath(), world);
			try {
				copyFolder(mapFolder, dir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bukkit.getServer().unloadWorld(Bukkit.getWorld(world), true);
			}			
		}, 20L);
		}
	
	}
