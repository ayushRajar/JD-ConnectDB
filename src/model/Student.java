package model;

/**
 * Student record represents a student entity in the system.
 * @param id The unique identifier (auto-generated)
 * @param name The student's name
 * @param score The student's score
 * @param grade The student's grade (single character)
 * @param active Whether the student is active
 */
public record Student(int id, String name, float score, char grade, boolean active) {}
