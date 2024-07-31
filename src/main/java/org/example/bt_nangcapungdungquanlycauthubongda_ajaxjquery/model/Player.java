package org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bt_nangcapungdungquanlycauthubongda_ajaxjquery.validate.ValidAge;
import org.hibernate.validator.constraints.Range;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    @Pattern(regexp = "^[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9 ]+$", message = "Ko được chứa ký tự đặc biệt")
    @Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự")
    private String name;

    @ValidAge(message = "tuổi phải lớn hơn 16 và bé hơn 100")
    private Date dob;

    @Range(min = 0, message = "Kinh nghiệp phải là số nguyên dương")
    private String experience;

    @NotBlank(message = "Không được để trống vị trí")
    private String position;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;


}
