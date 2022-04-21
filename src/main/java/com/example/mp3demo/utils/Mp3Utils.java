package com.example.mp3demo.utils;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.images.Artwork;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/21
 */
public class Mp3Utils {
    public static void main(String[] args) {
        try {
            MP3File file = new MP3File("D:\\Demo_code\\mp3Demo\\src\\main\\resources\\music\\Voices of the Chord - 澤野弘之.mp3");
            System.out.println(file);
            Tag tag = file.getTag();
            TagField title = tag.getFirstField("TIT2");
            TagField tpe1 = tag.getFirstField("TPE1");
            TagField talb = tag.getFirstField("TALB");
            System.out.println(title);
            System.out.println(tpe1);
            System.out.println(talb);

            Artwork firstArtwork = tag.getFirstArtwork();
            byte[] binaryData = firstArtwork.getBinaryData();
            Image img= Toolkit.getDefaultToolkit().createImage(binaryData, 0,binaryData.length);
            System.out.println("img----" + binaryData);
            ImageIcon icon = new ImageIcon(img);
            FileOutputStream fos = new FileOutputStream("D:\\Demo_code\\mp3Demo\\src\\main\\resources\\image\\image1.png");
            fos.write(binaryData);
            fos.close();
/*            AbstractID3v2Tag id3v2Tag = file.getID3v2Tag();
            AbstractID3v2Frame frame = (AbstractID3v2Frame)id3v2Tag.getFrame("APIC");
            FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
            byte[]  imageData = body.getImageData();
            Image img= Toolkit.getDefaultToolkit().createImage(imageData, 0,imageData.length);
            System.out.println("img----" + imageData);*/
/*            ImageIcon icon = new ImageIcon(img);
            FileOutputStream fos = new FileOutputStream("/resource/image");
            fos.write(imageData);
            fos.close();
            System.out.println("width:"+icon.getIconWidth());
            System.out.println("height:"+icon.getIconHeight());*/
        }
        catch(TagException e){
            throw new RuntimeException(e);
        } catch(CannotReadException e){
            throw new RuntimeException(e);
        } catch(InvalidAudioFrameException e){
            throw new RuntimeException(e);
        } catch(ReadOnlyFileException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
