package com.sportnova.app.model

// ==========================================
// SPORTNOVA RICH MOCK DATA
// ==========================================

object MockSportsData {

    val currentAthlete = AthleteProfile(
        studentName = "Sanket Bodke",
        studentId = "SN-ATH-2026-042",
        collegeName = "MIT Academy of Engineering",
        department = "Computer Science & Engineering",
        academicYear = "3rd Year",
        primarySport = SportType.BASKETBALL,
        athleteLevel = "College Varsity Captain",
        eventsParticipated = 14,
        matchesPlayed = 38,
        wins = 26,
        totalPoints = 1840,
        collegeRank = 3,
        bio = "All-court playmaker and 3-point specialist. Leading the MIT AOE Basketball Varsity squad in the 2026 State University circuit."
    )

    val liveBasketballMatch = LiveMatch(
        id = "LM-001",
        sport = SportType.BASKETBALL,
        tournamentName = "Pune Inter-College Championship Final",
        team1Name = "MIT AOE Titans",
        team1College = "MIT Academy of Engineering",
        team1Score = 74,
        team2Name = "COEP Warriors",
        team2College = "College of Engineering Pune",
        team2Score = 71,
        timeRemaining = "Q4 01:45",
        isLive = true,
        venue = "MIT Central Indoor Arena, Court 1",
        matchEvents = listOf(
            MatchEvent("01:45", "Sanket Bodke hits clutch stepback 3-pointer from deep right wing!", "MIT AOE Titans", 3),
            MatchEvent("02:15", "Defensive rebound secured by MIT Titans Captain", "MIT AOE Titans", 0),
            MatchEvent("02:40", "COEP Warriors fast break converted with reverse layup", "COEP Warriors", 2),
            MatchEvent("03:10", "Timeout called by COEP Head Coach", "COEP Warriors", 0),
            MatchEvent("03:45", "MIT AOE converts both free-throws following technical foul", "MIT AOE Titans", 2)
        )
    )

    val liveSoccerMatch = LiveMatch(
        id = "LM-002",
        sport = SportType.SOCCER,
        tournamentName = "University Cup Semi-Final",
        team1Name = "PICT Thunder",
        team1College = "PICT Pune",
        team1Score = 2,
        team2Name = "DY Patil Knights",
        team2College = "DY Patil University",
        team2Score = 1,
        timeRemaining = "2nd Half 78'",
        isLive = true,
        venue = "Shivaji Sports Complex Stadium"
    )

    val allEvents = listOf(
        SportEvent(
            id = "EVT-2026-01",
            title = "Inter-College Sports Fest 2026",
            sport = SportType.BASKETBALL,
            competitionType = CompetitionType.INTER_COLLEGE,
            organizerCollege = "MIT Academy of Engineering",
            dateRange = "Sept 15 - 18, 2026",
            location = "MIT AOE Sports Arena, Pune",
            participantsCount = 380,
            maxParticipants = 500,
            registrationDeadline = "Closes in 2 days",
            status = EventStatus.CLOSING_SOON,
            description = "The flagship sports festival bringing together over 30 leading engineering and technology colleges. Features division playoffs, live commentary, national scouts, and certified officiating.",
            eligibility = "Undergraduate & Post-graduate college students with verified ID cards.",
            fee = "Free for Affiliated Colleges"
        ),
        SportEvent(
            id = "EVT-2026-02",
            title = "Annual Department Sports Shield",
            sport = SportType.SOCCER,
            competitionType = CompetitionType.INTRA_COLLEGE,
            organizerCollege = "MIT Academy of Engineering",
            dateRange = "Sept 22 - 25, 2026",
            location = "Main Sports Ground, MIT Campus",
            participantsCount = 240,
            maxParticipants = 320,
            registrationDeadline = "Sept 18, 2026",
            status = EventStatus.REGISTRATION_OPEN,
            description = "Intra-college department battle for supremacy! Computer Engineering, Mechanical, Civil, ENTC, and Chemical go head-to-head for the prestigious Department Sports Shield."
        ),
        SportEvent(
            id = "EVT-2026-03",
            title = "Pune University T20 Cricket Cup",
            sport = SportType.CRICKET,
            competitionType = CompetitionType.INTER_COLLEGE,
            organizerCollege = "Pune University Sports Board",
            dateRange = "Oct 02 - 10, 2026",
            location = "University International Turf Ground",
            participantsCount = 512,
            maxParticipants = 600,
            registrationDeadline = "Sept 25, 2026",
            status = EventStatus.REGISTRATION_OPEN,
            description = "Premier university-level T20 tournament. 32 collegiate squads competing in a knock-out format under floodlights."
        ),
        SportEvent(
            id = "EVT-2026-04",
            title = "Inter-Department Badminton Open",
            sport = SportType.BADMINTON,
            competitionType = CompetitionType.INTRA_COLLEGE,
            organizerCollege = "Student Sports Council",
            dateRange = "Sept 28 - 29, 2026",
            location = "Indoor Badminton Complex",
            participantsCount = 64,
            maxParticipants = 64,
            registrationDeadline = "Completed",
            status = EventStatus.COMPLETED,
            description = "Singles and Doubles tournament for all academic departments. Medals and university ranking credits awarded to finalists."
        ),
        SportEvent(
            id = "EVT-2026-05",
            title = "Maharashtra State Athletics Meet",
            sport = SportType.ATHLETICS,
            competitionType = CompetitionType.STATE_VARSITY,
            organizerCollege = "State Directorate of Sports",
            dateRange = "Oct 15 - 18, 2026",
            location = "Balewadi National Stadium",
            participantsCount = 720,
            maxParticipants = 800,
            registrationDeadline = "Oct 05, 2026",
            status = EventStatus.UPCOMING,
            description = "State-level track & field championships covering 100m sprint, 4x100m relay, long jump, high jump, shotput, and 1500m distance."
        ),
        SportEvent(
            id = "EVT-2026-06",
            title = "Grandmaster Collegiate Chess Clash",
            sport = SportType.CHESS,
            competitionType = CompetitionType.INTER_COLLEGE,
            organizerCollege = "COEP Mind Sports Club",
            dateRange = "Sept 30, 2026",
            location = "COEP Heritage Auditorium",
            participantsCount = 120,
            maxParticipants = 150,
            registrationDeadline = "Sept 26, 2026",
            status = EventStatus.REGISTRATION_OPEN,
            description = "FIDE-standard rapid chess championship featuring the sharpest student minds across the university circuit."
        )
    )

    val collegeTeams = listOf(
        CollegeTeam(
            id = "TM-01",
            teamName = "MIT AOE Titans",
            collegeName = "MIT Academy of Engineering",
            category = "Intra & Varsity",
            sport = SportType.BASKETBALL,
            winRate = "84% Win Rate",
            squadCount = 15,
            isSelected = true
        ),
        CollegeTeam(
            id = "TM-02",
            teamName = "COEP Warriors",
            collegeName = "College of Engineering Pune",
            category = "Inter-College Division A",
            sport = SportType.BASKETBALL,
            winRate = "81% Win Rate",
            squadCount = 16,
            isSelected = false
        ),
        CollegeTeam(
            id = "TM-03",
            teamName = "PICT Thunder",
            collegeName = "Pune Institute of Computer Tech",
            category = "Inter-College Division A",
            sport = SportType.SOCCER,
            winRate = "76% Win Rate",
            squadCount = 18,
            isSelected = false
        ),
        CollegeTeam(
            id = "TM-04",
            teamName = "DY Patil Knights",
            collegeName = "DY Patil University",
            category = "Inter-College Division B",
            sport = SportType.CRICKET,
            winRate = "72% Win Rate",
            squadCount = 20,
            isSelected = false
        ),
        CollegeTeam(
            id = "TM-05",
            teamName = "Sinhgad Lions",
            collegeName = "Sinhgad Institute",
            category = "Inter-College Division B",
            sport = SportType.VOLLEYBALL,
            winRate = "69% Win Rate",
            squadCount = 14,
            isSelected = false
        )
    )

    val collegeRankings = listOf(
        CollegeRanking(1, "MIT Academy of Engineering", "MIT AOE", 4850, 14, 8, 5, "82%"),
        CollegeRanking(2, "College of Engineering Pune", "COEP", 4620, 12, 10, 6, "79%"),
        CollegeRanking(3, "Pune Institute of Computer Tech", "PICT", 4190, 11, 7, 8, "75%"),
        CollegeRanking(4, "DY Patil University", "DYPU", 3780, 8, 9, 7, "71%"),
        CollegeRanking(5, "Sinhgad College of Engineering", "SCOE", 3450, 6, 8, 9, "68%")
    )

    val departmentRankings = listOf(
        DepartmentRanking(1, "Computer Engineering", "COMP", 1480, 5, 3, 1),
        DepartmentRanking(2, "Mechanical Engineering", "MECH", 1320, 4, 4, 2),
        DepartmentRanking(3, "Electronics & Telecomm", "ENTC", 1190, 3, 2, 4),
        DepartmentRanking(4, "Civil Engineering", "CIVIL", 980, 2, 3, 1),
        DepartmentRanking(5, "Chemical Engineering", "CHEM", 740, 1, 1, 2)
    )

    val achievements = listOf(
        Achievement(
            id = "ACH-01",
            title = "State Championship Gold",
            eventName = "Maharashtra Inter-Varsity Basketball 2025",
            tier = AchievementTier.GOLD,
            dateEarned = "Dec 2025",
            pointsAwarded = 500,
            description = "Finished #1 with an undefeated 8-0 tournament record."
        ),
        Achievement(
            id = "ACH-02",
            title = "Tournament MVP Award",
            eventName = "Pune Collegiate League 2025",
            tier = AchievementTier.MVP,
            dateEarned = "Oct 2025",
            pointsAwarded = 350,
            description = "Averaged 24.5 PPG, 8.2 APG, and 3.1 Steals per contest."
        ),
        Achievement(
            id = "ACH-03",
            title = "Department Shield Winner",
            eventName = "Annual Department Sports Clash",
            tier = AchievementTier.WINNER,
            dateEarned = "Sept 2025",
            pointsAwarded = 300,
            description = "Led Computer Science department to triumph in the Finals."
        ),
        Achievement(
            id = "ACH-04",
            title = "University Silver Medalist",
            eventName = "West Zone Inter-College 3v3 Hoops",
            tier = AchievementTier.SILVER,
            dateEarned = "Mar 2025",
            pointsAwarded = 250,
            description = "Runners-up in the high-intensity collegiate 3v3 circuit."
        ),
        Achievement(
            id = "ACH-05",
            title = "Official College Representative",
            eventName = "All-India Inter-University Games",
            tier = AchievementTier.REPRESENTATIVE,
            dateEarned = "Jan 2025",
            pointsAwarded = 200,
            description = "Selected to represent university at national sports federation."
        )
    )

    val certificates = listOf(
        DigitalCertificate(
            id = "CERT-2025-001",
            certificateNumber = "SN-CERT-2025-GOLD-0941",
            studentName = "Sanket Bodke",
            eventName = "Maharashtra Inter-Varsity Basketball Championship",
            rankOrRole = "Tournament Champion (Gold Medalist)",
            collegeName = "MIT Academy of Engineering",
            dateIssued = "18th December 2025",
            sport = SportType.BASKETBALL,
            verificationUrl = "https://sportnova.edu/verify/SN-CERT-2025-GOLD-0941"
        ),
        DigitalCertificate(
            id = "CERT-2025-002",
            certificateNumber = "SN-CERT-2025-MVP-4421",
            studentName = "Sanket Bodke",
            eventName = "Pune Collegiate League MVP Honors",
            rankOrRole = "Most Valuable Player (MVP)",
            collegeName = "MIT Academy of Engineering",
            dateIssued = "24th October 2025",
            sport = SportType.BASKETBALL,
            verificationUrl = "https://sportnova.edu/verify/SN-CERT-2025-MVP-4421"
        ),
        DigitalCertificate(
            id = "CERT-2025-003",
            certificateNumber = "SN-CERT-2025-DEPT-1123",
            studentName = "Sanket Bodke",
            eventName = "Annual Department Shield 2025",
            rankOrRole = "Winning Team Captain",
            collegeName = "MIT Academy of Engineering",
            dateIssued = "28th September 2025",
            sport = SportType.BASKETBALL,
            verificationUrl = "https://sportnova.edu/verify/SN-CERT-2025-DEPT-1123"
        )
    )

    val notifications = listOf(
        AppNotification(
            id = "NOTIF-01",
            title = "Registration Deadline Alert",
            message = "Registration for Inter-College Sports Fest 2026 closes in 48 hours. Submit your final squad roster now.",
            category = NotificationCategory.REGISTRATION,
            timeAgo = "10m ago",
            isRead = false,
            actionText = "Review Roster"
        ),
        AppNotification(
            id = "NOTIF-02",
            title = "Match Starting in 30 Mins",
            message = "MIT AOE Titans vs COEP Warriors tips off at Court 1. Warm-ups are underway.",
            category = NotificationCategory.MATCHES,
            timeAgo = "25m ago",
            isRead = false,
            actionText = "Live Scoreboard"
        ),
        AppNotification(
            id = "NOTIF-03",
            title = "Registration Approved 🎉",
            message = "Your entry for Pune University T20 Cup has been officially verified by the Sports Committee.",
            category = NotificationCategory.REGISTRATION,
            timeAgo = "3h ago",
            isRead = true,
            actionText = "View Pass"
        ),
        AppNotification(
            id = "NOTIF-04",
            title = "New Achievement Unlocked!",
            message = "You earned the 'Tournament MVP' honor. Check your sports identity profile and trophy cabinet.",
            category = NotificationCategory.ACHIEVEMENTS,
            timeAgo = "1d ago",
            isRead = true,
            actionText = "View Trophy"
        ),
        AppNotification(
            id = "NOTIF-05",
            title = "Intra-College Department Clash",
            message = "Computer Engineering vs Mechanical Engineering Soccer Semifinal scheduled for tomorrow at 4 PM.",
            category = NotificationCategory.EVENTS,
            timeAgo = "2d ago",
            isRead = true,
            actionText = "Match Details"
        )
    )

    val leaderboards = listOf(
        LeaderboardEntry(1, "Michael Brown", "MIT AOE Titans", SportType.BASKETBALL, 8825200, 32),
        LeaderboardEntry(2, "Sarah Connor", "COEP Warriors", SportType.SOCCER, 7420100, 28),
        LeaderboardEntry(3, "Sanket Bodke", "MIT AOE Titans", SportType.BASKETBALL, 6840000, 26),
        LeaderboardEntry(4, "David Miller", "PICT Thunder", SportType.CRICKET, 5910400, 22),
        LeaderboardEntry(5, "Elena Rostova", "DY Patil Knights", SportType.BADMINTON, 4920000, 19)
    )
}
