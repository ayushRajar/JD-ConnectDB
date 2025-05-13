package model;

/**
 * Course record represents a course entity in the system.
 * @param id The unique identifier (auto-generated)
 * @param title The course title
 * @param credits The number of credits for the course
 * @param section The course section (single character)
 * @param available Whether the course is available
 */
public record Course(int id, String title, float credits, char section, boolean available) {}
