package com.example.mp3demo.utils;

import org.apache.ibatis.cache.Cache;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Base64;

/**
 * @Description: 这个类用于处理flac格式的音乐
 * @Author: Sitweling
 * @CreateTime: 2022/4/22
 */
public class FlacUtils {

    public static void main(String[] args) {
        try {
            FlacFileReader flacFileReader = new FlacFileReader();
            AudioFile audioFile = flacFileReader.read(new File("D:\\Demo_code\\mp3Demo\\src\\main\\resources\\music\\Colorful Dreams! Colorful Smiles!.flac"));
            Tag tag2 = audioFile.getTag();
            System.out.println(tag2.getFirstField("TITLE"));
            System.out.println(tag2.getFirstField("ARTIST"));
            System.out.println(tag2);
            //提取音乐文件中的封面，并且转化成Base64格式化字符串，写入json中，传递给前端
            Artwork firstArtwork = tag2.getFirstArtwork();
            byte[] binaryData = firstArtwork.getBinaryData();
            String s = Base64.getEncoder().encodeToString(binaryData);
            String filePath= "D:\\Demo_code\\mp3Demo\\src\\main\\resources\\image\\json1.json";
            File file1 = new File(filePath);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file1));
            writer.write(s);
            writer.flush();
            writer.close();

        }catch (CannotReadException e) {
            throw new RuntimeException(e);
        } catch (TagException e) {
            throw new RuntimeException(e);
        } catch (InvalidAudioFrameException e) {
            throw new RuntimeException(e);
        } catch (ReadOnlyFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
