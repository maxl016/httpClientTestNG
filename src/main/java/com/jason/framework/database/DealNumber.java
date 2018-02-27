package com.jason.framework.database;

public class DealNumber {
	public static final int ASC = 0;
	public static final int DESC = 1;

	public static final int getPartFloor(double x) {
		int pf = (int) Math.floor(x);
		return pf;
	}

	public static final int getPartCeil(double x) {
		int pc = (int) Math.ceil(x);
		return pc;
	}

	public static final int getPartInteger(double x) {
		String xs = String.valueOf(x);
		String pfs = DealString.getParamLeft(xs, ".");
		int pf = Integer.parseInt(pfs);
		return pf;
	}

	public static final double getPartFloat(double x) {
		String xs = String.valueOf(x);
		String pfs = "0" + xs.substring(xs.indexOf("."), xs.length());
		double pf = Double.parseDouble(pfs);
		return pf;
	}

	public static final boolean isInteger(double x) {
		double f = getPartFloat(x);
		if (f == 0.0D) {
			return true;
		}
		return false;
	}

	public static final double roundCalc(String roundtype, double v) {
		double value = 0.0D;
		if (roundtype.equals("Rounding")) {
			value = Math.round(v);
			return value;
		}
		if (roundtype.equals("Round Up")) {
			value = Math.ceil(v);
			return value;
		}
		if (roundtype.equals("Round Down")) {
			value = Math.floor(v);
			return value;
		}
		if (roundtype.equals("No Round")) {
			value = v;
			return value;
		}
		return value;
	}

	public static final double getSum(double[] x) {
		double sum = 0.0D;
		for (int i = 0; i < x.length; i++) {
			sum += x[i];
		}
		return sum;
	}

	public static final double getMax(double[] x) {
		double max = x[0];
		for (int i = 0; i < x.length; i++) {
			if (max < x[i]) {
				max = x[i];
			}
		}
		return max;
	}

	public static final int getMaxPos(double[] x) {
		double max = getMax(x);
		int p = -1;
		for (int i = 0; i < x.length; i++) {
			if (max == x[i]) {
				p = i;
				break;
			}
		}
		return p;
	}

	public static final int getMax(int[] x) {
		int max = x[0];
		for (int i = 0; i < x.length; i++) {
			if (max < x[i]) {
				max = x[i];
			}
		}
		return max;
	}

	public static final double getMin(double[] x) {
		double min = x[0];
		for (int i = 0; i < x.length; i++) {
			if (min > x[i]) {
				min = x[i];
			}
		}
		return min;
	}

	public static final int getMinPos(double[] x) {
		double min = getMin(x);
		int p = -1;
		for (int i = 0; i < x.length; i++) {
			if (min == x[i]) {
				p = i;
				break;
			}
		}
		return p;
	}

	public static final int getPos(double[] x, double v) {
		int p = -1;
		for (int i = 0; i < x.length; i++) {
			if (v == x[i]) {
				p = i;
				break;
			}
		}
		return p;
	}

	public static final int getMin(int[] x) {
		int min = x[0];
		for (int i = 0; i < x.length; i++) {
			if (min > x[i]) {
				min = x[i];
			}
		}
		return min;
	}

	public static final boolean contains(int[] x, int y) {
		boolean f = false;
		for (int i = 0; i < x.length; i++) {
			if (x[i] == y) {
				f = true;
				break;
			}
		}
		return f;
	}

	public static final double doubleFormat(double m, int num) {
		double p = 1.0D;
		for (int i = 0; i < num; i++) {
			p *= 10.0D;
		}
		double x = Math.round(m * p) / p;
		return x;
	}

	public static final void selectSort(int[] numbers, int order) {
		int size = numbers.length;
		for (int i = 0; i < size; i++) {
			int k = i;
			for (int j = size - 1; j > i; j--) {
				if ((order == 0) && (numbers[j] < numbers[k])) {
					k = j;
				}
				if ((order == 1) && (numbers[j] > numbers[k])) {
					k = j;
				}
			}
			int temp = numbers[i];
			numbers[i] = numbers[k];
			numbers[k] = temp;
		}
	}

	public static final void selectSortAsc(int[] numbers) {
		selectSort(numbers, 0);
	}

	public static final void selectSortDesc(int[] numbers) {
		selectSort(numbers, 1);
	}

	public static final void insertSort(int[] numbers, int order) {
		int size = numbers.length;
		for (int i = 1; i < size; i++) {
			int temp = numbers[i];
			if (order == 0) {
				for (int j = i; (j > 0) && (temp < numbers[(j - 1)]); j--) {
					numbers[j] = numbers[(j - 1)];
					numbers[j] = temp;
				}
			}
			if (order == 1) {
				for (int j = i; (j > 0) && (temp > numbers[(j - 1)]); j--) {
					numbers[j] = numbers[(j - 1)];
					numbers[j] = temp;
				}
			}
		}
	}

	public static final void insertSortAsc(int[] numbers) {
		insertSort(numbers, 0);
	}

	public static final void insertSortDesc(int[] numbers) {
		insertSort(numbers, 1);
	}

	public static final int cvtInt(String str) {
		if ((str == null) || (str.equals("")) || (str.equals("null"))) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static final double cvtDouble(String str) {
		if ((str == null) || (str.equals("")) || (str.equals("null"))) {
			return 0.0D;
		}
		return Double.parseDouble(str);
	}

	public static final int getRnd(int s, int t) {
		int x = (int) Math.round(Math.random() * (t - s) + s);
		return x;
	}

	public static final double getRnd(double s, double t) {
		double x = Math.random() * (t - s) + s;
		return x;
	}

	public static final double forecast(double[] p) {
		double total = 0.0D;
		for (int i = 0; i < p.length; i++) {
			total += p[i];
		}
		double ran = Math.random() * total;
		return ran;
	}

	public static final String forecast(String[] str, double[] p, double v) {
		double pt = 0.0D;
		for (int i = 0; i < p.length; i++) {
			pt += p[i];
			if (v < pt) {
				return str[i];
			}
		}
		return null;
	}

	public static final String forecast(String[] str, double[] p) {
		double pt = 0.0D;
		double ran = forecast(p);
		for (int i = 0; i < p.length; i++) {
			pt += p[i];
			if (ran < pt) {
				return str[i];
			}
		}
		return null;
	}

	public static final int[] uniguess(int[] ns, int n) {
		int[] f = new int[n];
		int[] t = ns;
		for (int i = 0; i < n; i++) {
			int p = (int) Math.round(Math.random() * (t.length - 1) + 0.0D);
			f[i] = t[p];
			t = cutArray(t, p);
		}
		return f;
	}

	public static final int[] uniguess(int a, int n) {
		int[] t = new int[a];
		for (int i = 0; i < a; i++) {
			t[i] = i;
		}
		int[] f = uniguess(t, n);
		return f;
	}

	public static final int[] repguess(int[] ns, int n) {
		int[] f = new int[n];
		int[] t = ns;
		for (int i = 0; i < n; i++) {
			int p = (int) Math.round(Math.random() * (t.length - 1) + 0.0D);
			f[i] = ns[p];
		}
		return f;
	}

	public static final int[] cutArray(int[] a, int n) {
		int[] c = new int[a.length - 1];
		int p = 0;
		for (int i = 0; i < a.length; i++) {
			if (i != n) {
				c[p] = a[i];
				p++;
			}
		}
		return c;
	}
}