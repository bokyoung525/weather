package com.zerobase.weather.controller;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 텍스트와 날짜를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "입력할 일기의 날짜", example = "2023-12-03") LocalDate date,
            @RequestBody String text
    ) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "날짜를 이용해서 DB에 저장된 일기 중 해당 날짜 일기 읽기")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "검색할 일기의 날짜", example = "2023-12-03") LocalDate date
    ) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "시작 날짜와 마지막 날짜를 이용해서 DB에 저장된 일기 중 해당 기간 일기 읽기")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "검색할 기간의 시작 날짜", example = "2023-12-03") LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "검색할 기간의 마지막 날짜", example = "2023-12-03") LocalDate endDate
    ) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "해당 날짜의 첫번째 일기의 내용 갱신")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "갱신할 일기의 날짜", example = "2023-12-03") LocalDate date,
            @RequestBody String text
    ) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "해당 날짜의 모든 일기 삭제")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                @Parameter(description = "삭제할 일기의 날짜", example = "2023-12-03") LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
