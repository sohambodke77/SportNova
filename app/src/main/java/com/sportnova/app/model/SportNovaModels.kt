package com.sportnova.app.model

// ==========================================
// SPORTNOVA DOMAIN DATA MODELS
// ==========================================

enum class SportType(val displayName: String, val iconEmoji: String) {
    ALL("All", "⚡"),
    BASKETBALL("Basketball", "🏀"),
    SOCCER("Soccer", "⚽"),
    CRICKET("Cricket", "🏏"),
    BADMINTON("Badminton", "🏸"),
    TENNIS("Tennis", "🎾"),
    VOLLEYBALL("Volleyball", "🏐"),
    ATHLETICS("Athletics", "🏃"),
    CHESS("Chess", "♟️"),
    KABADDI("Kabaddi", "🤼")
}

enum class EventStatus(val label: String) {
    REGISTRATION_OPEN("Registration Open"),
    CLOSING_SOON("Closing Soon"),
    LIVE("Live Now"),
    UPCOMING("Upcoming"),
    COMPLETED("Completed")
}

enum class CompetitionType {
    INTRA_COLLEGE,
    INTER_COLLEGE,
    STATE_VARSITY,
    NATIONAL_INVITATIONAL
}

data class SportEvent(
    val id: String,
    val title: String,
    val sport: SportType,
    val competitionType: CompetitionType,
    val organizerCollege: String,
    val dateRange: String,
    val location: String,
    val participantsCount: Int,
    val maxParticipants: Int,
    val registrationDeadline: String,
    val status: EventStatus,
    val fee: String = "Free for Students",
    val description: String,
    val eligibility: String = "Valid College ID required. Undergraduate and Graduate students eligible.",
    val rules: List<String> = listOf(
        "Standard university athletic federation rules apply",
        "Teams must arrive 30 minutes prior to scheduled match time",
        "Official sports kit and college ID card mandatory for participation",
        "Zero-tolerance policy on referee disputations and unsportsmanlike conduct"
    ),
    val scheduleTimeline: List<ScheduleItem> = listOf(
        ScheduleItem("Day 1 - 09:00 AM", "Opening Ceremony & Group Stage Qualifiers"),
        ScheduleItem("Day 2 - 10:30 AM", "Quarter-Finals & Semi-Final Clashes"),
        ScheduleItem("Day 3 - 04:00 PM", "Grand Championship Final & Medal Ceremony")
    ),
    val participatingColleges: List<String> = listOf(
        "MIT Academy of Engineering",
        "College of Engineering Pune (COEP)",
        "Pune Institute of Computer Technology (PICT)",
        "Sinhgad College of Engineering",
        "DY Patil University"
    )
)

data class ScheduleItem(
    val timeLabel: String,
    val activity: String
)

data class LiveMatch(
    val id: String,
    val sport: SportType,
    val tournamentName: String,
    val team1Name: String,
    val team1College: String,
    val team1Score: Int,
    val team2Name: String,
    val team2College: String,
    val team2Score: Int,
    val timeRemaining: String, // e.g. "Q4 01:45"
    val isLive: Boolean = true,
    val venue: String,
    val matchEvents: List<MatchEvent> = emptyList()
)

data class MatchEvent(
    val timestamp: String,
    val description: String,
    val scoringTeam: String,
    val pointsAdded: Int
)

data class AthleteProfile(
    val studentName: String,
    val studentId: String,
    val collegeName: String,
    val department: String,
    val academicYear: String,
    val primarySport: SportType,
    val athleteLevel: String, // e.g. "College Varsity Captain"
    val eventsParticipated: Int,
    val matchesPlayed: Int,
    val wins: Int,
    val totalPoints: Int,
    val collegeRank: Int,
    val bio: String
)

data class CollegeTeam(
    val id: String,
    val teamName: String,
    val collegeName: String,
    val category: String, // e.g. "Inter-College Division A"
    val sport: SportType,
    val winRate: String,
    val squadCount: Int,
    val isSelected: Boolean = false
)

enum class AchievementTier(val label: String) {
    GOLD("Gold Medal"),
    SILVER("Silver Medal"),
    BRONZE("Bronze Medal"),
    MVP("Tournament MVP"),
    WINNER("Championship Winner"),
    REPRESENTATIVE("College Representative")
}

data class Achievement(
    val id: String,
    val title: String,
    val eventName: String,
    val tier: AchievementTier,
    val dateEarned: String,
    val pointsAwarded: Int,
    val description: String
)

data class DigitalCertificate(
    val id: String,
    val certificateNumber: String,
    val studentName: String,
    val eventName: String,
    val rankOrRole: String,
    val collegeName: String,
    val dateIssued: String,
    val sport: SportType,
    val verificationUrl: String,
    val isVerified: Boolean = true
)

enum class NotificationCategory(val label: String) {
    ALL("All"),
    EVENTS("Events"),
    REGISTRATION("Registration"),
    MATCHES("Matches"),
    ACHIEVEMENTS("Achievements")
}

data class AppNotification(
    val id: String,
    val title: String,
    val message: String,
    val category: NotificationCategory,
    val timeAgo: String,
    val isRead: Boolean = false,
    val actionText: String? = null
)

data class LeaderboardEntry(
    val rank: Int,
    val athleteName: String,
    val collegeName: String,
    val sport: SportType,
    val points: Int,
    val winCount: Int
)

data class CollegeRanking(
    val rank: Int,
    val collegeName: String,
    val shortCode: String,
    val points: Int,
    val gold: Int,
    val silver: Int,
    val bronze: Int,
    val winRate: String
)

data class DepartmentRanking(
    val rank: Int,
    val departmentName: String,
    val shortCode: String,
    val points: Int,
    val gold: Int,
    val silver: Int,
    val bronze: Int
)
