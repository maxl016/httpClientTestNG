SELECT a.execute_status FROM acc_order_detail a WHERE  a.create_time LIKE '2016-10-18%' AND a.in_account_id IN 
  (SELECT 
    account_id 
  FROM
    p2p_account p 
  WHERE p.account_type='FA' AND p.user_id IN 
    (SELECT 
      user_id 
    FROM
      user_register r 
    WHERE r.user_login_name ='91610202579'))