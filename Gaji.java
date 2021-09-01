import java.util.*;
import java.text.*;

class GolonganUpah {
	public String golongan;
	public double upah;

	GolonganUpah(String golongan, double upah) {
		this.golongan = golongan;
		this.upah = upah;
	}
}

class JamKerja {
	public int day = 8;
	public int weekday = 6;
	public int weekhour = this.day * this.weekday;
}

class Pegawai {
	public String nama;
	public String golongan;
	public int[] jamKerjaPerMinggu = new int[4];

	Pegawai(String nama, String golongan, int[] jamKerjaPerMinggu) {
		this.nama = nama;
		this.golongan = golongan;
		this.jamKerjaPerMinggu = jamKerjaPerMinggu;
	}
}

class Gaji {
	public GolonganUpah[] golonganUpah = new GolonganUpah[4];
	public Pegawai[] pegawai = new Pegawai[1];

	public void initGolonganUpah() {
		String[] golongan = { "ob", "driver", "staff", "pimpinan" };
		double[] upah = { 15_000, 20_000, 28_000, 40_000 };

		int golonganLength = golongan.length;
		int instanceGolonganUpahLength = this.golonganUpah.length;

		if (golonganLength == instanceGolonganUpahLength) {
			for (int i = 0; i < golonganLength; i++) {
				this.golonganUpah[i] = new GolonganUpah(golongan[i], upah[i]);
			}
		}
	}

	public void initInputPegawai() {
		System.out.println("\n:: MASUKKAN DATA PEGAWAI ::");
		System.out.println("-------------------------------------------------\n");

		int[] jamKerjaPerMinggu = new int[4];
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < this.pegawai.length; i++) {
			System.out.print("nama pegawai		 : ");
			String nama = scanner.nextLine();

			System.out.print("golongan pegawai	 : ");
			String golongan = scanner.nextLine();

			for (int j = 0; j < jamKerjaPerMinggu.length; j++) {
				System.out.print("Jam kerja minggu ke " + (j + 1) + "	 : ");
				jamKerjaPerMinggu[j] = scanner.nextInt();
			}

			this.pegawai[i] = new Pegawai(nama, golongan, jamKerjaPerMinggu);
		}

		scanner.close();
	}

	public void initShowPegawai() {
		System.out.println("\n:: DATA PEGAWAI ::");
		System.out.println("-------------------------------------------------\n");
		for (int i = 0; i < this.pegawai.length; i++) {
			System.out.println("nama pegawai 			 : " + this.pegawai[i].nama);
			System.out.println("golongan pegawai 		 : " + this.pegawai[i].golongan);
			System.out.println("\n:: JAM KERJA ::\n");

			for (int j = 0; j < this.pegawai[i].jamKerjaPerMinggu.length; j++) {
				System.out.println(
						"Total jam kerja minggu ke " + (j + 1) + "	 : " + this.pegawai[i].jamKerjaPerMinggu[j]);
			}
		}

		System.out.println();
	}

	public void calculateUpahPegawai() {
		JamKerja jamKerja = new JamKerja();
		DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");

		double upahGolonganPegawai = 0;
		double upahPerLembur = 0;
		double totalUpahPegawai = 0;
		double totalUpahLembur = 0;
		int totalJamKerja = 0;

		for (int i = 0; i < this.pegawai[0].jamKerjaPerMinggu.length; i++) {
			if (this.pegawai[0].jamKerjaPerMinggu[i] > jamKerja.weekhour) {
				upahPerLembur += 1000;
			} else {
				upahPerLembur += 0;
			}

			totalJamKerja += this.pegawai[0].jamKerjaPerMinggu[i];
		}

		for (int i = 0; i < this.golonganUpah.length; i++) {
			if (this.golonganUpah[i].golongan.equals(this.pegawai[0].golongan)) {
				upahGolonganPegawai = (int) this.golonganUpah[i].upah;
				totalUpahPegawai = (int) (this.golonganUpah[i].upah * totalJamKerja);
			}
		}

		if (upahPerLembur != 0) {
			totalUpahLembur = (totalJamKerja - 48) * upahPerLembur;
		}

		totalUpahPegawai = (totalUpahPegawai + totalUpahLembur);

		System.out.println("\n:: GAJI PEGAWAI ::\n");

		System.out.println("Golongan Gaji			 : " + decimalFormat.format(upahGolonganPegawai));
		System.out.println("Upah Lembur			 : " + decimalFormat.format(upahPerLembur));
		System.out.println("Total Upah Lembur		 : " + decimalFormat.format(totalUpahLembur));
		System.out.println("Total Gaji Pegawai 		 : " + decimalFormat.format(totalUpahPegawai));
	}

	public static void main(String[] args) {
		Gaji gaji = new Gaji();

		gaji.initGolonganUpah();
		gaji.initInputPegawai();
		gaji.initShowPegawai();
		gaji.calculateUpahPegawai();
	}
}