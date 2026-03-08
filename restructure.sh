#!/bin/bash
set -e

BASE="src/main/java/com/example/ms_super_odette"
PKG="com.example.ms_super_odette"

# ── 1. Create new directories ──────────────────────────────────────────────
mkdir -p \
  $BASE/controller/{enrollment,grade,observation,schedule,student,subject,teacher,classgroup} \
  $BASE/service/{auth,enrollment,grade,observation,schedule,student,subject,teacher,classgroup}

# ── 2. Rename files before moving (schedule + subject convention fixes) ────

# Schedule
mv $BASE/service/ScheduleDispatcher.java      $BASE/service/ScheduleContext.java
mv $BASE/service/ScheduleService.java         $BASE/service/ScheduleStrategy.java
mv $BASE/service/StudentScheduleService.java  $BASE/service/StudentScheduleStrategy.java
mv $BASE/service/TeacherScheduleService.java  $BASE/service/TeacherScheduleStrategy.java

# Subject
mv $BASE/service/SubjectDispatcher.java       $BASE/service/SubjectContext.java
mv $BASE/service/SubjectService.java          $BASE/service/SubjectStrategy.java
mv $BASE/service/StudentSubjectService.java   $BASE/service/StudentSubjectStrategy.java
mv $BASE/service/TeacherSubjectService.java   $BASE/service/TeacherSubjectStrategy.java

# Schedule controller references ScheduleDispatcher — rename class inside file too
sed -i "s|ScheduleDispatcher|ScheduleContext|g"           $BASE/controller/ScheduleController.java
sed -i "s|scheduleDispatcher|scheduleContext|g"           $BASE/controller/ScheduleController.java

# Fix class names and internal references inside the renamed files
sed -i "s|class ScheduleDispatcher|class ScheduleContext|g"               $BASE/service/ScheduleContext.java
sed -i "s|interface ScheduleService|interface ScheduleStrategy|g"         $BASE/service/ScheduleStrategy.java
sed -i "s|implements ScheduleService|implements ScheduleStrategy|g"       $BASE/service/StudentScheduleStrategy.java
sed -i "s|implements ScheduleService|implements ScheduleStrategy|g"       $BASE/service/TeacherScheduleStrategy.java
sed -i "s|List<ScheduleService>|List<ScheduleStrategy>|g"                 $BASE/service/ScheduleContext.java
sed -i "s|ScheduleService service|ScheduleStrategy service|g"             $BASE/service/ScheduleContext.java
sed -i "s|class StudentScheduleService|class StudentScheduleStrategy|g"   $BASE/service/StudentScheduleStrategy.java
sed -i "s|class TeacherScheduleService|class TeacherScheduleStrategy|g"   $BASE/service/TeacherScheduleStrategy.java

sed -i "s|class SubjectDispatcher|class SubjectContext|g"                 $BASE/service/SubjectContext.java
sed -i "s|interface SubjectService|interface SubjectStrategy|g"           $BASE/service/SubjectStrategy.java
sed -i "s|implements SubjectService|implements SubjectStrategy|g"         $BASE/service/StudentSubjectStrategy.java
sed -i "s|implements SubjectService|implements SubjectStrategy|g"         $BASE/service/TeacherSubjectStrategy.java
sed -i "s|implements SubjectService|implements SubjectStrategy|g"         $BASE/service/SecretarySubjectService.java
sed -i "s|List<SubjectService>|List<SubjectStrategy>|g"                   $BASE/service/SubjectContext.java
sed -i "s|SubjectService service|SubjectStrategy service|g"               $BASE/service/SubjectContext.java
sed -i "s|class StudentSubjectService|class StudentSubjectStrategy|g"     $BASE/service/StudentSubjectStrategy.java
sed -i "s|class TeacherSubjectService|class TeacherSubjectStrategy|g"     $BASE/service/TeacherSubjectStrategy.java

# Fix references in SubjectController
sed -i "s|SubjectDispatcher|SubjectContext|g"     $BASE/controller/SubjectController.java
sed -i "s|subjectDispatcher|subjectContext|g"     $BASE/controller/SubjectController.java

# ── 3. Move controllers ────────────────────────────────────────────────────
mv $BASE/controller/EnrollmentController.java   $BASE/controller/enrollment/
mv $BASE/controller/GradeController.java        $BASE/controller/grade/
mv $BASE/controller/ObservationController.java  $BASE/controller/observation/
mv $BASE/controller/ScheduleController.java     $BASE/controller/schedule/
mv $BASE/controller/StudentController.java      $BASE/controller/student/
mv $BASE/controller/SubjectController.java      $BASE/controller/subject/
mv $BASE/controller/TeacherController.java      $BASE/controller/teacher/
mv $BASE/controller/ClassGroupController.java   $BASE/controller/classgroup/

# ── 4. Move services ───────────────────────────────────────────────────────
# auth
mv $BASE/service/AuthService.java                 $BASE/service/auth/
mv $BASE/service/RedisSessionService.java         $BASE/service/auth/
mv $BASE/service/SessionCookieService.java        $BASE/service/auth/
mv $BASE/service/SessionService.java              $BASE/service/auth/

# enrollment
mv $BASE/service/EnrollmentMapper.java            $BASE/service/enrollment/
mv $BASE/service/SecretaryEnrollmentService.java  $BASE/service/enrollment/
mv $BASE/service/StudentEnrollmentService.java    $BASE/service/enrollment/
mv $BASE/service/StudentRegistrationService.java  $BASE/service/enrollment/

# grade
mv $BASE/service/GradeContext.java                $BASE/service/grade/
mv $BASE/service/GradeMapper.java                 $BASE/service/grade/
mv $BASE/service/GradeStrategy.java               $BASE/service/grade/
mv $BASE/service/SharedStudentGradeService.java   $BASE/service/grade/
mv $BASE/service/StudentGradeStrategy.java        $BASE/service/grade/
mv $BASE/service/TeacherGradeStrategy.java        $BASE/service/grade/
mv $BASE/service/TeacherGradeUpsertService.java   $BASE/service/grade/

# observation
mv $BASE/service/ObservationContext.java          $BASE/service/observation/
mv $BASE/service/ObservationMapper.java           $BASE/service/observation/
mv $BASE/service/ObservationStrategy.java         $BASE/service/observation/
mv $BASE/service/SharedObservationService.java    $BASE/service/observation/
mv $BASE/service/StudentObservationStrategy.java  $BASE/service/observation/

# schedule
mv $BASE/service/ScheduleContext.java             $BASE/service/schedule/
mv $BASE/service/ScheduleStrategy.java            $BASE/service/schedule/
mv $BASE/service/StudentScheduleStrategy.java     $BASE/service/schedule/
mv $BASE/service/TeacherScheduleStrategy.java     $BASE/service/schedule/

# student
mv $BASE/service/StudentContext.java              $BASE/service/student/
mv $BASE/service/StudentMapper.java               $BASE/service/student/
mv $BASE/service/StudentStrategy.java             $BASE/service/student/
mv $BASE/service/TeacherStudentStrategy.java      $BASE/service/student/
[ -f $BASE/service/SecretaryStudentStrategy.java ] && \
  mv $BASE/service/SecretaryStudentStrategy.java  $BASE/service/student/

# subject
mv $BASE/service/SubjectContext.java              $BASE/service/subject/
mv $BASE/service/SubjectStrategy.java             $BASE/service/subject/
mv $BASE/service/StudentSubjectStrategy.java      $BASE/service/subject/
mv $BASE/service/TeacherSubjectStrategy.java      $BASE/service/subject/
mv $BASE/service/SecretarySubjectService.java     $BASE/service/subject/

# teacher + classgroup
mv $BASE/service/TeacherMapper.java               $BASE/service/teacher/
mv $BASE/service/TeacherService.java              $BASE/service/teacher/
mv $BASE/service/ClassGroupService.java           $BASE/service/classgroup/

# ── 5. Fix package declarations in moved files ─────────────────────────────
declare -A MOVES=(
  ["controller.EnrollmentController"]="controller.enrollment"
  ["controller.GradeController"]="controller.grade"
  ["controller.ObservationController"]="controller.observation"
  ["controller.ScheduleController"]="controller.schedule"
  ["controller.StudentController"]="controller.student"
  ["controller.SubjectController"]="controller.subject"
  ["controller.TeacherController"]="controller.teacher"
  ["controller.ClassGroupController"]="controller.classgroup"
  ["service.AuthService"]="service.auth"
  ["service.RedisSessionService"]="service.auth"
  ["service.SessionCookieService"]="service.auth"
  ["service.SessionService"]="service.auth"
  ["service.EnrollmentMapper"]="service.enrollment"
  ["service.SecretaryEnrollmentService"]="service.enrollment"
  ["service.StudentEnrollmentService"]="service.enrollment"
  ["service.StudentRegistrationService"]="service.enrollment"
  ["service.GradeContext"]="service.grade"
  ["service.GradeMapper"]="service.grade"
  ["service.GradeStrategy"]="service.grade"
  ["service.SharedStudentGradeService"]="service.grade"
  ["service.StudentGradeStrategy"]="service.grade"
  ["service.TeacherGradeStrategy"]="service.grade"
  ["service.TeacherGradeUpsertService"]="service.grade"
  ["service.ObservationContext"]="service.observation"
  ["service.ObservationMapper"]="service.observation"
  ["service.ObservationStrategy"]="service.observation"
  ["service.SharedObservationService"]="service.observation"
  ["service.StudentObservationStrategy"]="service.observation"
  ["service.ScheduleContext"]="service.schedule"
  ["service.ScheduleStrategy"]="service.schedule"
  ["service.StudentScheduleStrategy"]="service.schedule"
  ["service.TeacherScheduleStrategy"]="service.schedule"
  ["service.StudentContext"]="service.student"
  ["service.StudentMapper"]="service.student"
  ["service.StudentStrategy"]="service.student"
  ["service.TeacherStudentStrategy"]="service.student"
  ["service.SecretaryStudentStrategy"]="service.student"
  ["service.SubjectContext"]="service.subject"
  ["service.SubjectStrategy"]="service.subject"
  ["service.StudentSubjectStrategy"]="service.subject"
  ["service.TeacherSubjectStrategy"]="service.subject"
  ["service.SecretarySubjectService"]="service.subject"
  ["service.TeacherMapper"]="service.teacher"
  ["service.TeacherService"]="service.teacher"
  ["service.ClassGroupService"]="service.classgroup"
)

for OLD_PKG in "${!MOVES[@]}"; do
  NEW_PKG="${MOVES[$OLD_PKG]}"
  CLASS=$(echo "$OLD_PKG" | awk -F'.' '{print $NF}')
  FILE=$(find $BASE -name "${CLASS}.java" 2>/dev/null | head -1)
  [ -z "$FILE" ] && continue
  sed -i "s|^package ${PKG}\.${OLD_PKG%.*};|package ${PKG}.${NEW_PKG};|" "$FILE"
done

# ── 6. Fix all imports across the whole codebase ───────────────────────────
for OLD_PKG in "${!MOVES[@]}"; do
  NEW_PKG="${MOVES[$OLD_PKG]}"
  CLASS=$(echo "$OLD_PKG" | awk -F'.' '{print $NF}')
  find $BASE -name "*.java" -exec \
    sed -i "s|import ${PKG}\.${OLD_PKG};|import ${PKG}.${NEW_PKG}.${CLASS};|g" {} \;
done

# Fix imports for the old names that were renamed (so any file importing
# the old class name also gets the updated import path + class name)
declare -A RENAMES=(
  ["service.ScheduleDispatcher"]="service.schedule.ScheduleContext"
  ["service.ScheduleService"]="service.schedule.ScheduleStrategy"
  ["service.StudentScheduleService"]="service.schedule.StudentScheduleStrategy"
  ["service.TeacherScheduleService"]="service.schedule.TeacherScheduleStrategy"
  ["service.SubjectDispatcher"]="service.subject.SubjectContext"
  ["service.SubjectService"]="service.subject.SubjectStrategy"
  ["service.StudentSubjectService"]="service.subject.StudentSubjectStrategy"
  ["service.TeacherSubjectService"]="service.subject.TeacherSubjectStrategy"
)

for OLD_PKG in "${!RENAMES[@]}"; do
  NEW_FULL="${RENAMES[$OLD_PKG]}"
  find $BASE -name "*.java" -exec \
    sed -i "s|import ${PKG}\.${OLD_PKG};|import ${PKG}.${NEW_FULL};|g" {} \;
done

# ── 7. Cleanup ─────────────────────────────────────────────────────────────
rmdir $BASE/strategy 2>/dev/null || true

echo "✓ Restructure + renames complete"