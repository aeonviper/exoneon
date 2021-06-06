package exoneon.annotation;

public class MusicIndustry {

	private int version;
	private String name;

	@Musician(value = "Blake Shelton")
	public void say() {
		System.out.println("Say");
	}

	@Musician(value = "Gwen Stefani", method = "Singing")
	public void sing() {
		System.out.println("Sing");
	}

	@Description
	@MusicBand(value = { @Musician("A"), @Musician("B") })
	public void setSinger(Singer singer) {
		System.out.println("Singer is " + singer.getClass().getName());
	}

}
