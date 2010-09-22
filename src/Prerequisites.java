import java.util.*;

public class Prerequisites {
	public String[] orderClasses(String... classSchedule) {
		Map<String, String[]> prerequisitesPerCourse = new HashMap<String, String[]>();
		Set<String> allCourses = new HashSet<String>();

		for (String element : classSchedule) {
			String course = element.substring(0, element.indexOf(':'));
			String[] prerequisites = element.endsWith(":") ? new String[0] : element.substring(element.indexOf(':') + 2).split("[ ]");

			prerequisitesPerCourse.put(course, prerequisites);
			allCourses.add(course);
			allCourses.addAll(Arrays.asList(prerequisites));
		}

		try {
			String[] orderedCourses = allCourses.toArray(new String[0]);
			Arrays.sort(orderedCourses, COURSE_ORDER(prerequisitesPerCourse));
			return orderedCourses;
		} catch (CycleDetected e) {
			return new String[0];
		} catch (PrerequisiteNotReferenced e) {
			return new String[0];
		}
	}

	private Comparator<String> COURSE_ORDER(final Map<String, String[]> prerequisitesPerCourse) {
		return new Comparator<String>() {
			@Override
			public int compare(String c1, String c2) {
				if (isBefore(c1, c2) && isBefore(c2, c1)) {
					throw new CycleDetected();
				}
				if (isBefore(c1, c2)) {
					return -1;
				}
				if (isBefore(c2, c1)) {
					return +1;
				}

				int diff = number(c1) - number(c2);
				if (0 != diff) {
					return diff;
				}
				return departement(c1).compareTo(departement(c2));
			}

			private boolean isBefore(String course1, String course2) {
				String[] prerequisites = prerequisitesPerCourse.get(course2);
				if (null == prerequisites) {
					throw new PrerequisiteNotReferenced();
				}
				for (String prerequisite : prerequisites) {
					if (prerequisite.equals(course1)) {
						return true;
					}
				}
				return false;
			}

			private String departement(String course) {
				return course.substring(0, course.length() - 3);
			}

			private int number(String course) {
				return Integer.parseInt(course.substring(course.length() - 3));
			}
		};
	}

	static class CycleDetected extends RuntimeException {
	}

	static class PrerequisiteNotReferenced extends RuntimeException {
	}
}
