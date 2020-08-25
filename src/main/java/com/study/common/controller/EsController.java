package com.study.common.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * https://blog.csdn.net/jacksonary/article/details/82729556
 * 2019-12-02
 */
@RequestMapping("/es")
@RestController
public class EsController {

    @Autowired
    private TransportClient client;

    /**
     * 添加文档
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("id") String id, @RequestParam("name") String name) {
        try {
            // 构造ES的文档，这里注意startObject()开始构造，结束构造一定要加上endObject()
            XContentBuilder content = XContentFactory.jsonBuilder().startObject().
                    field("id", id)
                    .field("name", name)
                    .endObject();
            IndexResponse result = client.prepareIndex("book", "novel")
                    .setSource(content).get();
            return new ResponseEntity<>(result.getId(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id查询
     *
     * @param id book id
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> get(@RequestParam("id") String id) {
        GetResponse result = client.prepareGet("book", "novel", id).get();
        return new ResponseEntity<>(result.getSource(), HttpStatus.OK);
    }

    /**
     * 根据id删除book
     *
     * @param id book id
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value = "id") String id) {
        DeleteResponse result = client.prepareDelete("book", "novel", id).get();
        return new ResponseEntity<>(result.getResult().toString(), HttpStatus.OK);
    }


    /**
     * 更新文档，这里的Book可以不管他，这样做是为了解决PUT请求的问题，随便搞
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody Book book) {
        System.out.println(book);
        // 根据id查询
        UpdateRequest updateRequest = new UpdateRequest("book", "novel", book.getNumberOfPages() + "");
        try {
            XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
            contentBuilder.field("name", book.toString());
            contentBuilder.endObject();
            updateRequest.doc(contentBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 进行更新
        UpdateResponse updateResponse = new UpdateResponse();
        try {
            updateResponse = client.update(updateRequest).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(updateResponse.getResult().toString(), HttpStatus.OK);
    }

}
